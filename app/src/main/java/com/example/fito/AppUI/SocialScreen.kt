package com.example.fito.AppUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fito.components.BottomNavigationBar
import com.example.fito.ui.theme.FitoTheme


@Composable
fun SearchFriendsBar(input: String, onInputChange: (String) -> Unit){
    TextField(
        value = input,
        onValueChange = onInputChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp),


        placeholder = {
            Text("Tìm bạn bè bằng số điện thoại",

                )

        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (input.isNotEmpty()) {
                IconButton(onClick = { onInputChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Xóa")
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(50),
    )
}


@Composable
fun FriendCircle(){
    var friendInput by remember { mutableStateOf("") }
    var friendNumber by remember { mutableStateOf(0) }
    val friendLimit: Int = 10
    Scaffold ()
    {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,


        ){
            SearchFriendsBar(input = friendInput, onInputChange = {friendInput = it})
            Spacer(modifier=Modifier.height(10.dp))
            Text(
                text="Kết nối và hỗ trợ lẫn nhau trên hành trình rèn luyện sức khỏe của bạn",
                modifier = Modifier.padding(start=15.dp, end=15.dp),
                fontWeight=FontWeight.SemiBold,
                fontSize=18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier=Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start=20.dp, end=20.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    text="Bạn bè của tôi",
                    fontSize= 18.sp,
                    fontWeight= FontWeight.Normal
                )

                Text(
                    text="$friendNumber/$friendLimit bạn bè",
                    fontSize= 18.sp
                )

            }
            Spacer(modifier=Modifier.height(10.dp))
            if(friendNumber==0){
                Text(
                    text="Hiện tại không có người bạn nào"
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun rv8() {
    FitoTheme {
        FriendCircle()
    }
}