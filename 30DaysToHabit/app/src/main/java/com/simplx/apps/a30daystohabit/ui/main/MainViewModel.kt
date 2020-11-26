package com.simplx.apps.a30daystohabit.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository

class MainViewModel @ViewModelInject constructor
    (private val repository: HabitTracerRepository) : ViewModel() {

    var habitList: LiveData<List<Habit>>? = null

    init {
        habitList = repository.getAllHabits()
    }

    fun deleteHabit(id: Int) {
        repository.deleteHabit(id)
    }

}