package com.example.fito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fito.data.repository.MealRepository

class MealVMFactory(
    private val repository: MealRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealVM::class.java)) {
            return MealVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
