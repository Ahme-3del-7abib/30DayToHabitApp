package com.simplx.apps.a30daystohabit.model

import android.app.Application
import androidx.lifecycle.*
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository
import com.simplx.apps.a30daystohabit.room.ApplicationDataBase
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class HabitTracerViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: HabitTracerRepository
    var habitList: LiveData<List<Habit>>? = null
    var habitArcList: LiveData<List<ArchivedHabit>>? = null

    var currentHabit: LiveData<Habit>? = null

    val returnedVal = MutableLiveData<Days>()

    init {
        val habitDatabase = ApplicationDataBase.getInstance(application).habitApplicationDao()
        repository = HabitTracerRepository(habitDatabase)
        habitList = repository.getAllHabits()
        habitArcList = repository.getAllArchivedHabits()
        currentHabit = repository.getCurrentHabit()
    }

    fun insertHabit(habit: Habit) = viewModelScope.launch(IO) {
        repository.insertHabit(habit)
    }

    fun updateHabit(habit: Habit) = viewModelScope.launch(IO) {
        repository.updateHabit(habit)
    }

    fun deleteHabit(id: Int) = viewModelScope.launch(IO) {
        repository.deleteHabit(id)
    }

    fun insertDay(days: Days) = viewModelScope.launch(IO) {
        repository.insertDay(days)
    }

    fun updateDay(days: Days) = viewModelScope.launch(IO) {
        repository.updateDay(days)
    }

    fun getCurrentDayById(id: Int) = viewModelScope.launch(IO) {
        returnedVal.postValue(repository.getCurrentDayById(id))
    }

    fun insertArchivedHabit(archivedHabit: ArchivedHabit) = viewModelScope.launch(IO) {
        repository.insertArchivedHabit(archivedHabit)
    }

    fun deleteArchivedHabit(id: Int) = viewModelScope.launch(IO) {
        repository.deleteArchivedHabit(id)
    }

}