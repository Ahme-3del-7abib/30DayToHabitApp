package com.simplx.apps.a30daystohabit.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit

@Dao
interface HabitAppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHabit(habit: Habit)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateHabit(habit: Habit)

    @Query("DELETE FROM habits_table WHERE ID = :id")
    fun deleteHabit(id: Int)

    @Query("SELECT * FROM habits_table ORDER BY progress ASC")
    fun getAllActivities(): LiveData<List<Habit>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDay(day: Days)

    @Query("DELETE FROM days_table WHERE habit_id = :id")
    fun deleteDay(id: Int)

}