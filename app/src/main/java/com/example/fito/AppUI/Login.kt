package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.fito.ui.theme.FitoTheme

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onNavigateRegister: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    Box(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text="FiTo",
                fontSize=40.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text="Chào mừng trở lại",
                fontSize=25.sp,
                fontWeight = FontWeight.Bold,
            )
            Box(
                modifier=Modifier
                    .width(330.dp)

            ){
                Text(
                    text="Miễn là bạn muốn thay đổi chúng tôi sẽ đồng hành cùng bạn ",
                    fontSize=20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight=FontWeight.Light

                )
            }

            OutlinedTextField(
                leadingIcon={
                  Icon(
                      Icons.Rounded.AccountCircle,
                      contentDescription="")
                },
                value = username,
                onValueChange = { username = it },
                label = { Text(usernameError.ifEmpty{"Tên người dùng"},color = if (usernameError.isNotEmpty()) Red else Unspecified) },
                placeholder = { Text("Nhập tên người dùng của bạn") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )

            OutlinedTextField(
                leadingIcon={
                    Icon(
                        Icons.Rounded.Lock,
                        contentDescription="")
                },
                value = password,
                onValueChange = { password = it },
                label = { Text(passwordError.ifEmpty{"Mật khẩu"},color = if (passwordError.isNotEmpty()) Red else Unspecified) },
                placeholder = { Text("Nhập mật khẩu") },
                singleLine = true,
                modifier = Modifier.width(330.dp),
                visualTransformation=if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                trailingIcon={
                    val image =if (passwordVisible)
                        painterResource(id=R.drawable.visibility_24px)
                    else painterResource(id=R.drawable.visibility_off_24px)
                    Icon(
                        painter=image,
                        contentDescription = "",
                        modifier= Modifier
                            .clickable{
                                passwordVisible= !passwordVisible
                            }
                            .size(24.dp)

                    )
                }
            )
            Spacer(modifier=Modifier.height(20.dp))
            Button(
                onClick = { /* xử lí các th người dùng có nhập ko nhập và các logic sau khi bấm */},
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Đăng nhập",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }
            Spacer(modifier=Modifier.height(20.dp))

            Text(text="Quên mật khẩu?",
                fontWeight=FontWeight.Bold,
            modifier=Modifier.clickable{
                // xử lí logic quên mk
            }
            )
            Spacer(modifier=Modifier.height(20.dp))

            val annotatedText = buildAnnotatedString {
                append("Chưa có tài khoản? ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF000000),
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
                    //  Xử lý khi người dùng bấm dk
                })
        }
    }

}



@Preview
@Composable
fun rv2(){
    FitoTheme(){
        LoginScreen(
            onLoginSuccess = {},
            onNavigateRegister = {}
        )
    }
}