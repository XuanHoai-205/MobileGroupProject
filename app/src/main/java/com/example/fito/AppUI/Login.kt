package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.viewmodel.LoginVM

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateRegister: () -> Unit,
    viewModel: LoginVM = viewModel()
) {
    val username = viewModel.username
    val password = viewModel.password
    val passwordVisible = viewModel.passwordVisible
    val usernameError = viewModel.usernameError
    val passwordError = viewModel.passwordError
    val loginError = viewModel.loginError
    val isLoading = viewModel.isLoading

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(50.dp))

            Text("FiTo", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text("Chào mừng trở lại", fontSize = 25.sp, fontWeight = FontWeight.Bold)

            Box(modifier = Modifier.width(330.dp)) {
                Text(
                    text = "Chúng tôi sẽ đồng hành trên con đường thay đổi của bạn",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
            }

            OutlinedTextField(
                leadingIcon = {
                    Icon(Icons.Rounded.AccountCircle, contentDescription = "")
                },
                value = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = {
                    Text(
                        usernameError.ifEmpty { "Email đăng nhập" },
                        color = if (usernameError.isNotEmpty()) Red else Unspecified
                    )
                },
                placeholder = { Text("Nhập email") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )

            // PASSWORD
            OutlinedTextField(
                leadingIcon = {
                    Icon(Icons.Rounded.Lock, contentDescription = "")
                },
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = {
                    Text(
                        passwordError.ifEmpty { "Mật khẩu" },
                        color = if (passwordError.isNotEmpty()) Red else Unspecified
                    )
                },
                placeholder = { Text("Nhập mật khẩu") },
                singleLine = true,
                modifier = Modifier.width(330.dp),
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(id = R.drawable.visibility_24px)
                    else painterResource(id = R.drawable.visibility_off_24px)

                    Icon(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable { viewModel.onTogglePasswordVisible() }
                            .size(24.dp)
                    )
                }
            )

            if (loginError.isNotEmpty()) {
                Text(
                    text = loginError,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.onLoginClick {
                        onLoginSuccess()
                    }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text(
                        text = "Đăng nhập",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Quên mật khẩu?",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            val annotatedText = buildAnnotatedString {
                append("Chưa có tài khoản? ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Đăng kí")
                }
            }

            Text(
                text = annotatedText,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    onNavigateRegister()
                }
            )
        }
    }
}

@Preview
@Composable
fun rv2() {
    FitoTheme {
        LoginScreen(
            onLoginSuccess = {},
            onNavigateRegister = {}
        )
    }
}
