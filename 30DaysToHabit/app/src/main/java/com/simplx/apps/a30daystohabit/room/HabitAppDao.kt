package com.simplx.apps.a30daystohabit.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit

@Dao
interface HabitAppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHabit(habit: Habit)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateHabit(habit: Habit)

    @Query("DELETE FROM habits_table WHERE ID = :id")
    suspend fun deleteHabit(id: Int)

    @Query("SELECT * FROM habits_table ORDER BY progress ASC")
    fun getAllActivities(): LiveData<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Days)

    @Query("DELETE FROM days WHERE habit_id = :id")
    suspend fun deleteDay(id: Int)

}