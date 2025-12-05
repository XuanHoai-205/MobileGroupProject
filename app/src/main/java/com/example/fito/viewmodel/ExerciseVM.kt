package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fito.data.model.ExerciseEntity
import com.example.fito.data.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import java.util.Calendar

class ExerciseVM(private val repository: ExerciseRepository) : ViewModel() {

    data class UserExercise(
        val id: Int?,
        val name: String,
        val calories: Int,
        val completed: Boolean = false,
        val date: Long
    )


    private val todayExercisesMutable = MutableStateFlow<List<UserExercise>>(emptyList())
    val todayExercises: StateFlow<List<UserExercise>> = todayExercisesMutable


    private val totalCaloriesMutable = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = totalCaloriesMutable


    var selectedExercise by mutableStateOf("")
        private set

    var duration by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
        private set

    var expanded by mutableStateOf(false)
        private set

    var showDialog by mutableStateOf(false)
        private set


    init {
        loadExercisesForToday()
        loadTotalCaloriesForToday()
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


    private fun loadExercisesForToday() {
        val todayStart = getStartOfDay()
        val todayEnd = getEndOfDay()
        viewModelScope.launch {
            repository.getTodayExercises(todayStart, todayEnd).collect { exercises ->
                todayExercisesMutable.value = exercises.map {
                    UserExercise(
                        id = it.id,
                        name = it.name,
                        calories = it.caloriesBurned,
                        completed = it.isCompleted,
                        date = it.date
                    )
                }
            }
        }
    }

    private fun loadTotalCaloriesForToday() {
        val todayStart = getStartOfDay()
        val todayEnd = getEndOfDay()
        viewModelScope.launch {
            repository
                .getTodayTotalCaloriesBurned(todayStart, todayEnd)
                .filterNotNull()
                .collect {
                    totalCaloriesMutable.value = it
                }
        }
    }


    fun onExerciseChange(value: String){
        selectedExercise=value
    }
    fun onDurationChange(value: String){
        duration =value
    }
    fun onCaloriesChange(value: String){
        calories=value
    }
    fun onDropdownClick(){
        expanded=!expanded
    }

    fun onDialogClose(){
        showDialog=false
    }
    fun onDialogOpen() {
        showDialog = true
    }


    fun submitExercise(){
        if (selectedExercise.isNotEmpty() && duration.isNotEmpty() && calories.isNotEmpty()) {
            val calBurned = calories.toIntOrNull() ?: 0
            val dur = duration.toIntOrNull() ?: 0

            if (calBurned <= 0 || dur <= 0) return

            val exercise = ExerciseEntity(
                id = null,
                name = selectedExercise,
                caloriesBurned = calBurned,
                durationMinutes = dur,
                date = System.currentTimeMillis(),
                isCompleted = false
            )

            viewModelScope.launch {
                repository.insertExercise(exercise)
            }


            selectedExercise = ""
            duration = ""
            calories = ""
            showDialog = true
        }
    }

    fun toggleExerciseCompleted(exercise: UserExercise) {
        viewModelScope.launch {
            val updatedEntity = ExerciseEntity(
                id = exercise.id,
                name = exercise.name,
                durationMinutes = 0,
                caloriesBurned = exercise.calories,
                date = exercise.date,
                isCompleted = !exercise.completed
            )
            repository.updateExercise(updatedEntity)
        }
    }
}