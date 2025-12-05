package com.example.fito.data.repository

import com.example.fito.data.local.dao.MealDao
import com.example.fito.data.model.MealEntity
import kotlinx.coroutines.flow.Flow

class MealRepository(private val mealDao: MealDao) {

    suspend fun insertMeal(meal: MealEntity) {
        mealDao.insertMeal(meal)
    }

    fun getTodayMeals(startOfDay: Long, endOfDay: Long): Flow<List<MealEntity>> {
        return mealDao.getMealsForDay(startOfDay, endOfDay)
    }

    fun getTodayTotalCalories(startOfDay: Long, endOfDay: Long): Flow<Int?> {
        return mealDao.getTotalCaloriesForDay(startOfDay, endOfDay)
    }
}