package com.simplx.apps.a30daystohabit.repository

import androidx.lifecycle.LiveData
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

    fun getAllHabits(): LiveData<List<Habit>> {
        return appDao.getAllActivities()
    }

    suspend fun insertDay(days: Days) {
        appDao.insertDay(days)
    }

    suspend fun deleteDays(id: Int) {
        appDao.deleteDay(id)
    }

}