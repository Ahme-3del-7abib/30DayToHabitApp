package com.simplx.apps.a30daystohabit.ui.trace

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.repository.HabitTracerRepository

class TracerViewModel @ViewModelInject constructor
    (private val repository: HabitTracerRepository) : ViewModel() {

    var allDays = MutableLiveData<List<Days>>()
    val numOfDays = MutableLiveData<Int>()
    val numOfSuccessDays = MutableLiveData<Int>()
    val numOfFailedDays = MutableLiveData<Int>()
    val totalProgress = MutableLiveData<Double>()


    init {
    }

    fun getAllDaysById(id: Int) {
        allDays.postValue(repository.getAllDaysById(id))
    }

    fun getNumOfDaysById(id: Int) {
        numOfDays.postValue(repository.getNumOfDaysById(id))
    }

    fun getNumOfSuccessDaysById(id: Int) {
        numOfSuccessDays.postValue(repository.getNumOfSuccessDaysById(id))
    }

    fun getNumOfFailedById(id: Int) {
        numOfFailedDays.postValue(repository.getNumOfFailedById(id))
    }

    fun getTotalProgressById(id: Int) {
        totalProgress.postValue(repository.getTotalProgressById(id))
    }

    fun insertDay(days: Days) {
        repository.insertDay(days)
    }

    fun insertArchivedHabit(archivedHabit: ArchivedHabit) {
        repository.insertArchivedHabit(archivedHabit)
    }

    fun deleteHabit(id: Int) {
        repository.deleteHabit(id)
    }

}

