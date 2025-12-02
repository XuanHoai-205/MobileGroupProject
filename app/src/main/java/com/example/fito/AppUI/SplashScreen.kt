package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fito.ui.theme.FitoTheme


@Composable
fun FeatureBox(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .background(backgroundColor)
            .padding(16.dp)
    ) {


        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
@Composable
fun LoginRegisterRow(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Đăng nhập",
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onLoginClick() }
        )

        Text(text="/")
        Text(
            text = "Đăng ký",
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}
@Composable
fun FirstScreen(onNavigateLogin: () -> Unit, onNavigateRegister:()-> Unit) {

    Box(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(){
            Row(
            modifier=Modifier
                .padding(top=25.dp)
            ){
                Box(
                    modifier = Modifier
                        .size(width= 240.dp,height=60.dp)
                        .background(Color(0xFFA9A9A9  )),
                    contentAlignment = Alignment.Center
                ){
                    Text(text="Fito",
                        fontWeight=FontWeight.Bold,
                        fontSize=30.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .size(width= 250.dp,height=60.dp)
                        .background(Color(0xFFC0C0C0)),
                    contentAlignment = Alignment.Center

                ){
                    LoginRegisterRow(
                        onLoginClick = { onNavigateLogin() },
                        onRegisterClick = { onNavigateRegister() }
                    )
                }
            }
            Spacer(modifier=Modifier.height(25.dp))
            Text(
                text = "Bắt đầu hành trình giảm cân của chính bạn ngay bây giờ",
                fontSize = 25.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold

            )
            Spacer(modifier=Modifier.height(25.dp))
            Text(
                text="Đốt mỡ thừa, các bài tập cardio thích hợp và đặt mục tiêu luyện tập cùng bạn bè",
                textAlign = TextAlign.Center,
            )
            Spacer(modifier=Modifier.height(25.dp))
            Button(
                onClick = {
                    onNavigateRegister()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Bắt đầu hành trình",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier=Modifier.height(25.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    FeatureBox(
                        title = "Theo dõi lượng calo",
                        description = "Theo dõi lượng calo vào và ra của bạn cho việc giảm cân hiệu quả",
                        backgroundColor = Color(0xFFD3D3D3),
                        modifier = Modifier.weight(1f)
                    )
                    FeatureBox(

                        title = "Thư viện bài tập ",
                        description = "Danh sách bài tập tiêu thụ nhiều calo dành riêng cho việc giảm cân",
                        backgroundColor = Color(0xFF808080 ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    FeatureBox(
                        title = "Vòng bạn bè",
                        description = "Kết bạn, chia sẻ quá trình luyện tập và tạo động lực cùng nhau",
                        backgroundColor = Color(0xFF808080 ),
                        modifier = Modifier.weight(1f)
                    )
                    FeatureBox(
                        title = "Tập luyện cùng nhau",
                        description = "Tạo lịch tập cùng nhau mà bạn bè có thể tham gia",
                        backgroundColor = Color(0xFFD3D3D3),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier=Modifier.height(50.dp))
            Text(
                text="FiTo đồng hành trong quá trình thay đổi của bạn",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
            )
            }

        }
    }


@Preview
@Composable
fun rv1(){
    FitoTheme(){
        FirstScreen(
            onNavigateLogin = {},
            onNavigateRegister={}
        )
    }
}