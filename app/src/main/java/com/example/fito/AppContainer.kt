package com.example.fito

import android.content.Context
import com.example.fito.data.local.database.FitoDatabase
import com.example.fito.data.repository.AuthRepository
import com.example.fito.data.repository.ExerciseRepository
import com.example.fito.data.repository.MealRepository
import com.example.fito.data.repository.ScheduleRepository

interface AppContainer {
    val mealRepository: MealRepository
    val exerciseRepository: ExerciseRepository
    val scheduleRepository: ScheduleRepository
    val authRepository: AuthRepository
}
class AppDataContainer(private val context: Context) : AppContainer {

    private val database by lazy {
        FitoDatabase.getDatabase(context)
    }

    override val mealRepository: MealRepository by lazy {
        MealRepository(database.mealDao())
    }

    override val exerciseRepository: ExerciseRepository by lazy {
        ExerciseRepository(database.exerciseDao())
    }

    override val scheduleRepository: ScheduleRepository by lazy {
        ScheduleRepository(database.scheduleDao())
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepository()
    }
}


