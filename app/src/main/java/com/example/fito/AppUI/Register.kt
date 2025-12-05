package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Phone
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
import com.example.fito.viewmodel.RegisterVM
import kotlin.text.ifEmpty

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateLogin: () -> Unit,
    viewModel: RegisterVM = viewModel()
) {
    val email = viewModel.email
    val password = viewModel.password
    val username = viewModel.username
    val passwordVisible = viewModel.passwordVisible
    val usernameError = viewModel.usernameError
    val phoneError = viewModel.emailError
    val passwordError = viewModel.passwordError

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "FiTo",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Chào mừng bạn",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )

            Box(modifier = Modifier.width(330.dp)) {
                Text(
                    text = "Tạo tài khoản mới để bắt đầu cuộc hành trình",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                leadingIcon = {
                    Icon(Icons.Rounded.Phone, contentDescription = "")
                },
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = {
                    Text(
                        phoneError.ifEmpty { "Email đăng kí" },
                        color = if (phoneError.isNotEmpty()) Red else Unspecified
                    )
                },
                placeholder = { Text("Nhập số email của bạn") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )

            OutlinedTextField(
                leadingIcon = {
                    Icon(Icons.Rounded.AccountCircle, contentDescription = "")
                },
                value = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = {
                    Text(
                        usernameError.ifEmpty { "Tên người dùng" },
                        color = if (usernameError.isNotEmpty()) Red else Unspecified
                    )
                },
                placeholder = { Text("Nhập tên người dùng của bạn") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )

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
                visualTransformation =
                    if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                trailingIcon = {
                    val image =
                        if (passwordVisible)
                            painterResource(id = R.drawable.visibility_24px)
                        else
                            painterResource(id = R.drawable.visibility_off_24px)

                    Icon(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable { viewModel.togglePasswordVisible() }
                            .size(24.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.onRegisterClick {
                        onRegisterSuccess()
                    }
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Đăng ký",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            val annotatedText = buildAnnotatedString {
                append("Đã có tài khoản? ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Đăng nhập")
                }
            }

            Text(
                text = annotatedText,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    onNavigateLogin()
                }
            )
        }
    }
}

@Preview
@Composable
fun rv3() {
    FitoTheme {
        RegisterScreen(
            onRegisterSuccess = {},
            onNavigateLogin = {}
        )
    }
}
