package com.simplx.apps.a30daystohabit.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "days_table")
data class Days(

    @PrimaryKey(autoGenerate = true)
    val day_id: Int,

    @ForeignKey(
        entity = Habit::class,
        parentColumns = ["ID"],
        childColumns = ["habit_id"],
        onDelete = CASCADE
    )
    @ColumnInfo(name = "habit_id")
    val habit_id: Int,

    @ColumnInfo(name = "day_number")
    val day_number: Int,

    @ColumnInfo(name = "status")
    val status: String
)