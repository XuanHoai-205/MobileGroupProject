package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.fito.data.repository.AuthRepository

class RegisterVM : ViewModel() {

    private val authRepository = AuthRepository()

    var email by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    var emailError by mutableStateOf("")
        private set

    var usernameError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    fun onEmailChange(value: String) {
        email = value
        emailError = ""
    }

    fun onUsernameChange(value: String) {
        username = value
        usernameError = ""
    }

    fun onPasswordChange(value: String) {
        password = value
        passwordError = ""
    }

    fun togglePasswordVisible() {
        passwordVisible = !passwordVisible
    }

    fun onRegisterClick(onSuccess: () -> Unit) {
        var isValid = true

        if (email.isBlank()) {
            emailError = "Hãy nhập số điện thoại"
            isValid = false
        } else {
            emailError = ""
        }

        if (username.isBlank()) {
            usernameError = "Hãy nhập tên người dùng"
            isValid = false
        } else {
            usernameError = ""
        }

        if (password.length < 6) {
            passwordError = "Mật khẩu phải từ 6 ký tự"
            isValid = false
        } else {
            passwordError = ""
        }

        if (!isValid) return

        val email = if (email.contains("@")) email else "$email@gmail.com"

        authRepository.register(
            email = email,
            password = password,
            username = username
        ) { ok, msg ->
            if (ok) {
                onSuccess()
            } else {
                passwordError = msg ?: "Đăng ký thất bại"
            }
        }
    }
}
