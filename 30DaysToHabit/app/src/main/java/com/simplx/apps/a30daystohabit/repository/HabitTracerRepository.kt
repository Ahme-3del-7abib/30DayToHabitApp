package com.simplx.apps.a30daystohabit.repository

import androidx.lifecycle.LiveData
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.room.HabitAppDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitTracerRepository @Inject constructor(private val appDao: HabitAppDao) {

    fun insertHabit(habit: Habit) = GlobalScope.launch(IO) {
        appDao.insertHabit(habit)
    }

    fun updateHabit(habit: Habit) = GlobalScope.launch(IO) {
        appDao.updateHabit(habit)
    }

    fun deleteHabit(id: Int) = GlobalScope.launch(IO) {
        appDao.deleteHabit(id)
    }

    fun getCurrentHabit(): LiveData<Habit> {
        return appDao.getCurrentHabit()
    }

    fun getAllHabits(): LiveData<List<Habit>> {
        return appDao.getAllHabits()
    }

    fun insertDay(days: Days) = GlobalScope.launch(IO) {
        appDao.insertDay(days)
    }

    fun insertArchivedHabit(archivedHabit: ArchivedHabit) = GlobalScope.launch(IO) {
        appDao.insertArchivedHabit(archivedHabit)
    }

    fun deleteArchivedHabit(id: Int) = GlobalScope.launch(IO) {
        appDao.deleteArchivedHabit(id)
    }

    fun getAllArchivedHabits(): LiveData<List<ArchivedHabit>> {
        return appDao.getAllArchivedHabits()
    }

    fun getNumOfDaysById(id: Int): Int {
        return appDao.getNumOfDaysById(id)
    }

    fun getAllDaysById(id: Int): List<Days> {
        return appDao.getAllDaysById(id)
    }

    fun getNumOfSuccessDaysById(id: Int): Int {
        return appDao.getNumOfSuccessDaysById(id)
    }

    fun getNumOfFailedById(id: Int): Int {
        return appDao.getNumOfFailedById(id)
    }

    fun getTotalProgressById(id: Int): Double {
        return appDao.getTotalProgressById(id)
    }

}