package com.simplx.apps.a30daystohabit.ui.add

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository

class HabitsViewModel @ViewModelInject constructor
    (private val repository: HabitTracerRepository) : ViewModel() {

    var lastInsertedHabit: LiveData<Habit>? = null

    init {
        lastInsertedHabit = repository.getCurrentHabit()
    }

    fun insertHabit(habit: Habit) {
        repository.insertHabit(habit)
    }
}