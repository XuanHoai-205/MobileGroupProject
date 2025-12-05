package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fito.data.model.MealEntity
import com.example.fito.data.repository.MealRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar

class MealVM(
    private val repository: MealRepository
) : ViewModel() {


    var mealDetail by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
        private set

    var showCard by mutableStateOf(false)

    var showExitDialog by mutableStateOf(false)
        private set


    private val _todayTotalCalories = MutableStateFlow(0)
    val todayTotalCalories: StateFlow<Int> = _todayTotalCalories

    var mealType by mutableStateOf("")
        private set

    fun onMealTypeSelect(type: String) {
        mealType = type
    }
    fun onMealDetailSet(value: String) {
        mealDetail = value
    }

    fun onCaloriesChange(value: String) {
        calories = value
    }

    fun buttonClick() {
        showCard = true
    }

    fun closeCard() {
        showCard = false
        mealDetail = ""
        calories = ""
    }

    fun onExitButtonClick() {
        showExitDialog = true
    }

    fun confirmExit() {
        showCard = false
        showExitDialog = false
    }

    fun declineExit() {
        showExitDialog = false
    }
    init {
        loadTodayTotalCalories(getStartOfDay(), getEndOfDay())
    }
    private fun getStartOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun getEndOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }
    fun submitMeal() {
        val cal = calories.toIntOrNull() ?: 0
        if (mealType.isBlank()) return

        val meal = MealEntity(
            mealName = mealDetail,
            mealCalories = cal,
            mealType = mealType,
            date = System.currentTimeMillis()
        )

        viewModelScope.launch {
            repository.insertMeal(meal)


            showCard = false
            mealDetail = ""
            calories = ""
            mealType = ""
        }
    }

    fun loadTodayTotalCalories(startOfDay: Long, endOfDay: Long) {
        viewModelScope.launch {
            repository
                .getTodayTotalCalories(startOfDay, endOfDay)
                .filterNotNull()
                .collect {
                    _todayTotalCalories.value = it
                }
        }
    }

    fun getTodayMeals(startOfDay: Long, endOfDay: Long) =
        repository.getTodayMeals(startOfDay, endOfDay)


}
