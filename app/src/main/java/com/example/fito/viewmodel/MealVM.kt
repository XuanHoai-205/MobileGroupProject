package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MealVM : ViewModel() {
    var mealDetail by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
        private set

    var showCard by mutableStateOf(false)

    fun buttonClick(){
        showCard = true
    }
    fun closeCard() {
        showCard = false
        mealDetail = ""
        calories = ""
    }
    var showExitDialog by mutableStateOf(false)
        private set

    fun onMealDetailSet(value: String){
        mealDetail=value
    }

    fun onExitButtonClick(){
        showExitDialog=true
    }
    fun confirmExit() {
        showCard = false
        showExitDialog = false
    }
    fun declineExit() { showExitDialog = false }
    fun onCaloriesChange(value: String){
        calories=value
    }

    fun submitMeal(){
        showCard = false
        mealDetail = ""
        calories = ""
    }
}
