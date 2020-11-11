package com.simplx.apps.a30daystohabit.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archive_table")
data class ArchivedHabit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "arc_habit_id", defaultValue = "0")
    val id: Int? = null,

    @ColumnInfo(name = "arc_habit_name")
    val name: String,

    @ColumnInfo(name = "arc_habit_achievement")
    val achievement: Double,

    @ColumnInfo(name = "arc_habit_success_days")
    val successDays: Int,

    @ColumnInfo(name = "arc_habit_failed_days")
    val failedDays: Int

)