package com.simplx.apps.a30daystohabit.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit

@Database(
    entities = [Habit::class, Days::class, ArchivedHabit::class],
    version = 2,
    exportSchema = false
)
abstract class ApplicationDataBase : RoomDatabase() {
    
    abstract fun habitApplicationDao(): HabitAppDao
}