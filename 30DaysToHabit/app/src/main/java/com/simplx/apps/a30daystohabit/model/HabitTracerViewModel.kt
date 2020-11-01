package com.simplx.apps.a30daystohabit.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository
import com.simplx.apps.a30daystohabit.room.ApplicationDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitTracerViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: HabitTracerRepository
    private val habitList: LiveData<List<Habit>>

    init {
        val habitDatabase = ApplicationDataBase.getInstance(application).habitApplicationDao()
        repository = HabitTracerRepository(habitDatabase)
        habitList = repository.getAllHabits()
    }

    fun insertHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertHabit(habit)
    }

    fun updateHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateHabit(habit)
    }

    fun deleteHabit(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteHabit(id)
    }


    fun insertDay(days: Days) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertDay(days)
    }

    fun deleteDay(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteDays(id)
    }
}