package com.simplx.apps.a30daystohabit.repository

import androidx.lifecycle.LiveData
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.room.HabitAppDao

class HabitTracerRepository(private val appDao: HabitAppDao) {


    public suspend fun insertHabit(habit: Habit) {
        appDao.insertHabit(habit)
    }

    public suspend fun updateHabit(habit: Habit) {
        appDao.updateHabit(habit)
    }

    public suspend fun deleteHabit(id: Int) {
        appDao.deleteHabit(id)
    }

    public fun getAllHabits(): LiveData<List<Habit>> {
        return appDao.getAllActivities()
    }

    public suspend fun insertDay(days: Days) {
        appDao.insertDay(days)
    }

    public suspend fun deleteDays(id: Int) {
        appDao.deleteDay(id)
    }

}