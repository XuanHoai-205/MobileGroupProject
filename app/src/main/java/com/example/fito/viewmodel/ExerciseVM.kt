package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ExerciseVM : ViewModel() {

    data class UserExercise(
        val name: String,
        val calories: Int,
        var completed: Boolean = false
    )
    var addedExercises by mutableStateOf(listOf<UserExercise>())
        private set
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
    fun submitExercise(){
        if (selectedExercise.isNotEmpty() && calories.isNotEmpty()) {
            val newExercise = UserExercise(selectedExercise, calories.toIntOrNull() ?: 0)
            addedExercises = addedExercises + newExercise

            selectedExercise = ""
            duration = ""
            calories = ""
        }
    }
    fun toggleExerciseCompleted(exercise: UserExercise) {
        addedExercises = addedExercises.map {
            if (it == exercise) it.copy(completed = !it.completed)
            else it
        }
    }
    fun onDialogClose(){
        showDialog=false
    }
    fun onDialogOpen() {
        showDialog = true
    }



}
