package com.example.fito.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name="exercise_name")
    val name: String,
    @ColumnInfo(name="exercise_duration")
    val durationMinutes: Int,
    @ColumnInfo(name="exercise_calo")
    val caloriesBurned: Int,
    @ColumnInfo(name="exercise_date")
    val date: Long,
    @ColumnInfo(name="is_completed")
    val isCompleted: Boolean = false
)