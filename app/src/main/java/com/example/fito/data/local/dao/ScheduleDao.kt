package com.example.fito.data.local.dao

import androidx.room.*
import com.example.fito.data.model.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM schedules ORDER BY schedule_year, schedule_month, schedule_day, schedule_starttime ASC")
    fun getAllSchedules(): Flow<List<ScheduleEntity>>

    @Update
    suspend fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    suspend fun deleteSchedule(schedule: ScheduleEntity)
}