package com.example.fito.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow



class DashboardVM : ViewModel() {

    private val totalMeals = MutableStateFlow(0)
    val allMeals = totalMeals.asStateFlow()
    private val totalWorkouts = MutableStateFlow(0)
    val allWorkouts = totalWorkouts.asStateFlow()
    private val caloIn = MutableStateFlow(0)
    val calIn = caloIn.asStateFlow()
    private val caloOut = MutableStateFlow(0)
    val calOut = caloOut.asStateFlow()
    fun updateTotalMealsCalorie(totalMealCalories: Int) {

        caloIn.value = totalMealCalories

    }
    fun updateTotalExerciseCalorie(totalExerciseCalories: Int) {

        caloOut.value = totalExerciseCalories
    }
    fun logout(onLogout: () -> Unit) {

        onLogout()

    }

}

