package com.simplx.apps.a30daystohabit.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
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

    @Query("SELECT * FROM habits_table")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("SELECT * FROM habits_table WHERE ID = (SELECT MAX(ID) FROM habits_table)")
    fun getCurrentHabit(): LiveData<Habit>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Days)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArchivedHabit(archivedHabit: ArchivedHabit)

    @Query("DELETE FROM archive_table WHERE arc_habit_id = :id")
    suspend fun deleteArchivedHabit(id: Int)

    @Query("SELECT * FROM archive_table")
    fun getAllArchivedHabits(): LiveData<List<ArchivedHabit>>

    @Query("SELECT COUNT(*) FROM days_table WHERE habit_id = :id")
    fun getNumOfDaysById(id: Int): Int

    @Query("SELECT * FROM days_table WHERE habit_id = :id")
    fun getAllDaysById(id: Int): List<Days>

    @Query("SELECT COUNT(*) FROM days_table WHERE day_status = 'SUCCESS' AND habit_id = :id")
    fun getNumOfSuccessDaysById(id: Int): Int

    @Query("SELECT COUNT(*) FROM days_table WHERE day_status = 'FAILED' AND habit_id = :id")
    fun getNumOfFailedById(id: Int): Int

    @Query("SELECT SUM(progress) FROM days_table WHERE habit_id = :id")
    fun getTotalProgressById(id: Int): Double

}