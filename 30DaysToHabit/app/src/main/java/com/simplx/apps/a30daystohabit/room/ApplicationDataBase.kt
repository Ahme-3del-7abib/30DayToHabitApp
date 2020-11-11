package com.simplx.apps.a30daystohabit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var INSTANCE: ApplicationDataBase? = null

        fun getInstance(context: Context): ApplicationDataBase {

            val temInstance =
                INSTANCE

            if (temInstance != null) {
                return temInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDataBase::class.java,
                    "application_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}