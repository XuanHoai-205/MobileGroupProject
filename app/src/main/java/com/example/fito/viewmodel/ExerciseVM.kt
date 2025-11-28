package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ExerciseVM : ViewModel() {
    var selectedExercise by mutableStateOf("")
        private set

    var duration by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
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
    fun submitExercise(){

    }


}
