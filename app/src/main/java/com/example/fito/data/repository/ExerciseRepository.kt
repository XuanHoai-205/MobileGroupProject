package com.example.fito.data.repository

import com.example.fito.data.local.dao.ExerciseDao
import com.example.fito.data.model.ExerciseEntity
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    suspend fun insertExercise(exercise: ExerciseEntity) {
        exerciseDao.insertExercise(exercise)
    }
    suspend fun updateExercise(exercise: ExerciseEntity) {
        exerciseDao.updateExercise(exercise)
    }
    fun getTodayExercises(startOfDay: Long, endOfDay: Long): Flow<List<ExerciseEntity>> {
        return exerciseDao.getExercisesForDay(startOfDay, endOfDay)
    }

    fun getTodayTotalCaloriesBurned(startOfDay: Long, endOfDay: Long): Flow<Int?> {
        return exerciseDao.getTotalCaloriesBurnedForDay(startOfDay, endOfDay)
    }

}