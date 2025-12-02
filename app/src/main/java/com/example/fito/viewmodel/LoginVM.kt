package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
class LoginVM: ViewModel() {
        var username by mutableStateOf("")
            private set

        var password by mutableStateOf("")
            private set

        var passwordVisible by mutableStateOf(false)
            private set

        var usernameError by mutableStateOf("")
            private set

        var passwordError by mutableStateOf("")
            private set


        fun onUsernameChange(value: String) {
            username = value
            if (value.isNotEmpty()) {
                usernameError = ""
            }
        }

        fun onPasswordChange(value: String) {
            password = value
            if (value.isNotEmpty()) {
                passwordError = ""
            }
        }

        fun onTogglePasswordVisible() {
            passwordVisible = !passwordVisible
        }


        fun onLoginClick(onSuccess: () -> Unit) {
            var isValid = true

            if (username.isBlank()) {
                usernameError = "Hãy nhập tên người dùng"
                isValid = false
            }else{
                usernameError =""
            }

            if (password.isBlank()) {
                passwordError = "Hãy nhập mật khẩu"
                isValid = false
            }else{
                passwordError =""
            }

            if (isValid) {
                onSuccess(



                )
            }
        }
    }