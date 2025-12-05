package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.fito.data.repository.AuthRepository

class AuthVM : ViewModel() {

    private val repo = AuthRepository()

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var success by mutableStateOf(false)

    fun login(email: String, password: String) {
        isLoading = true
        repo.login(email, password) { ok, msg ->
            isLoading = false
            if (ok) success = true
            else error = msg ?: "Đăng nhập không thành công"
        }
    }

    fun register(email: String, password: String, username: String) {
        isLoading = true
        repo.register(email, password, username) { ok, msg ->
            isLoading = false
            if (ok) success = true
            else error = msg ?: "Đăng kí không thành công"
        }
    }

    fun logout() {
        repo.logout()
        success = false
    }

    fun isLoggedIn(): Boolean = repo.isLoggedIn()
}