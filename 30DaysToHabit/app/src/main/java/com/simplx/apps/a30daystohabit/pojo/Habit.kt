package com.simplx.apps.a30daystohabit.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits_table")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID", defaultValue = "0")
    val ID: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "desc")
    val desc: String,

    @ColumnInfo(name = "notification")
    val notification: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "progress")
    val progress: Int
)