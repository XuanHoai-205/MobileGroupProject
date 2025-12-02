package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class RegisterVM : ViewModel() {
    var phone by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    var phoneError by mutableStateOf("")
        private set

    var usernameError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    fun onPhoneChange(value: String) {
        phone = value
    }

    fun onUsernameChange(value: String) {
        username = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun togglePasswordVisible() {
        passwordVisible = !passwordVisible
    }

    fun onRegisterClick(onSuccess: () -> Unit) {
        var isValid = true

        if (phone.isBlank()) {
            phoneError = "Hãy nhập số điện thoại"
            isValid = false
        } else {
            phoneError = ""
        }

        if (username.isBlank()) {
            usernameError = "Hãy nhập tên người dùng"
            isValid = false
        } else {
            usernameError = ""
        }

        if (password.isBlank()) {
            passwordError = "Hãy nhập mật khẩu"
            isValid = false
        } else {
            passwordError = ""
        }

        if (isValid) {

            onSuccess()
        }
    }
}