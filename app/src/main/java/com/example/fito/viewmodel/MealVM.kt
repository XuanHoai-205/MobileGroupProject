package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MealVM : ViewModel() {
    var mealDetail by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
        private set

    fun onMealDetailSet(value: String){
        mealDetail=value
    }

    fun onCaloriesChange(value: String){
        calories=value
    }

    fun submitMeal(){

    }
}
