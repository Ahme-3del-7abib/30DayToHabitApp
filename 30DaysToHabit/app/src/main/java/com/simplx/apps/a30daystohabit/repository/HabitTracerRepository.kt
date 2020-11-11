package com.simplx.apps.a30daystohabit.repository

import androidx.lifecycle.LiveData
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.room.HabitAppDao


class HabitTracerRepository(private val appDao: HabitAppDao) {


    suspend fun insertHabit(habit: Habit) {
        appDao.insertHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        appDao.updateHabit(habit)
    }

    suspend fun deleteHabit(id: Int) {
        appDao.deleteHabit(id)
    }

    fun getCurrentHabit(): LiveData<Habit> {
        return appDao.getCurrentHabit()
    }

    fun getAllHabits(): LiveData<List<Habit>> {
        return appDao.getAllActivities()
    }

    suspend fun insertDay(days: Days) {
        appDao.insertDay(days)
    }

    suspend fun updateDay(days: Days) {
        appDao.updateDay(days)
    }

    fun getCurrentDayById(id: Int): Days {
        return appDao.getCurrentDayById(id)
    }

    suspend fun insertArchivedHabit(archivedHabit: ArchivedHabit) {
        appDao.insertArchivedHabit(archivedHabit)
    }

    suspend fun deleteArchivedHabit(id: Int) {
        appDao.deleteArchivedHabit(id)
    }

    fun getAllArchivedHabits(): LiveData<List<ArchivedHabit>> {
        return appDao.getAllArchivedHabits()
    }
}