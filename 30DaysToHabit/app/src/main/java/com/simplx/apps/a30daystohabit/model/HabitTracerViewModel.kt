package com.simplx.apps.a30daystohabit.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository
import com.simplx.apps.a30daystohabit.room.ApplicationDataBase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HabitTracerViewModel(application: Application) : ViewModel() {

    private var repository: HabitTracerRepository
    var habitList: LiveData<List<Habit>>? = null

    init {
        val habitDatabase = ApplicationDataBase.getInstance(application).habitApplicationDao()
        repository = HabitTracerRepository(habitDatabase)
        habitList = repository.getAllHabits()
    }

    fun insertHabit(habit: Habit) = GlobalScope.launch(IO) {
        repository.insertHabit(habit)
    }

    fun updateHabit(habit: Habit) = GlobalScope.launch(IO) {
        repository.updateHabit(habit)
    }

    fun deleteHabit(id: Int) = GlobalScope.launch(IO) {
        repository.deleteHabit(id)
    }

    fun insertDay(days: Days) = GlobalScope.launch(IO) {
        repository.insertDay(days)
    }

    fun deleteDay(id: Int) = GlobalScope.launch(IO) {
        repository.deleteDays(id)
    }
}