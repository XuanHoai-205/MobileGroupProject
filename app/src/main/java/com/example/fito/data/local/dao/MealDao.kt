
package com.example.fito.data.local.dao

import androidx.room.*
import com.example.fito.data.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Query("SELECT * FROM meals WHERE meal_date >= :startOfDay AND meal_date < :endOfDay ORDER BY meal_date DESC")
    fun getMealsForDay(startOfDay: Long, endOfDay: Long): Flow<List<MealEntity>>

    @Query("SELECT SUM(meal_calo) FROM meals WHERE meal_date >= :startOfDay AND meal_date < :endOfDay")
    fun getTotalCaloriesForDay(startOfDay: Long, endOfDay: Long): Flow<Int?>

    @Delete
    suspend fun deleteMeal(meal: MealEntity)
}