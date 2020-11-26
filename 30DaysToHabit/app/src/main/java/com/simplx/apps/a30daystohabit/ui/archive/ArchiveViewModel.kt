package com.simplx.apps.a30daystohabit.ui.archive

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository

class ArchiveViewModel @ViewModelInject constructor
    (private val repository: HabitTracerRepository) : ViewModel() {

    var habitArcList: LiveData<List<ArchivedHabit>>? = null

    init {
        habitArcList = repository.getAllArchivedHabits()
    }

    fun deleteArchivedHabit(id: Int) {
        repository.deleteArchivedHabit(id)
    }

}