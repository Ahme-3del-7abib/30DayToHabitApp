package com.simplx.apps.a30daystohabit.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
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

    @Query("SELECT * FROM habits_table")
    fun getAllActivities(): LiveData<List<Habit>>

    @Query("SELECT * FROM habits_table ORDER BY ID DESC LIMIT 1")
    fun getCurrentHabit(): LiveData<Habit>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDay(day: Days)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDay(day: Days)

    @Query("SELECT * FROM days_table WHERE habit_id = :id")
    fun getCurrentDayById(id: Int): Days

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArchivedHabit(archivedHabit: ArchivedHabit)

    @Query("DELETE FROM archive_table WHERE arc_habit_id = :id")
    fun deleteArchivedHabit(id: Int)

    @Query("SELECT * FROM archive_table")
    fun getAllArchivedHabits(): LiveData<List<ArchivedHabit>>
}