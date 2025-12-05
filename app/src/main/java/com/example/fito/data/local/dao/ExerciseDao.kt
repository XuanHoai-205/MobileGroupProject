package com.example.fito.data.local.dao

import androidx.room.*
import com.example.fito.data.model.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE exercise_date >= :startOfDay AND exercise_date < :endOfDay ORDER BY exercise_date DESC")
    fun getExercisesForDay(startOfDay: Long, endOfDay: Long): Flow<List<ExerciseEntity>>

    @Query("SELECT SUM(exercise_calo) FROM exercises WHERE exercise_date >= :startOfDay AND exercise_date < :endOfDay")
    fun getTotalCaloriesBurnedForDay(startOfDay: Long, endOfDay: Long): Flow<Int?>
    @Update
    suspend fun updateExercise(exercise: ExerciseEntity)
    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
}