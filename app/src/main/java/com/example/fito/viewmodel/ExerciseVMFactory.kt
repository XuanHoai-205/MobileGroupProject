package com.example.fito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fito.data.repository.ExerciseRepository

class ExerciseVMFactory(private val repository: ExerciseRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseVM::class.java)) {

            return ExerciseVM(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}