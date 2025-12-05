package com.example.fito.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class ScheduleEntity(

    @PrimaryKey(autoGenerate = true)
    val scheduleId: Int? = null,
    @ColumnInfo(name="schedule_name")
    val title: String,
    @ColumnInfo(name="schedule_day")
    val scheduleDay: Int,
    @ColumnInfo(name="schedule_month")
    val scheduleMonth: Int,
    @ColumnInfo(name="schedule_year")
    val scheduleYear: Int,
    @ColumnInfo(name="schedule_starttime")
    val startTime: String,
    @ColumnInfo(name="schedule_location")
    val location: String,
    @ColumnInfo(name="schedule_status")
    val status: String,
    @ColumnInfo(name="schedule_summary")
    val exercisesSummary: String,
    @ColumnInfo(name="schedule_timeset")
    val createdDate: Long = System.currentTimeMillis()
)