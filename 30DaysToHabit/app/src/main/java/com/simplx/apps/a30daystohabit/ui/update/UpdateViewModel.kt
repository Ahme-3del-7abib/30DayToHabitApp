package com.simplx.apps.a30daystohabit.ui.update

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository

class UpdateViewModel @ViewModelInject constructor
    (private val repository: HabitTracerRepository) : ViewModel() {

    fun updateHabit(habit: Habit) {
        repository.updateHabit(habit)
    }
}