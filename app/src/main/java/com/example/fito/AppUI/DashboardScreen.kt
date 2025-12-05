package com.example.fito.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.viewmodel.DashboardVM

@Composable
fun Dashboard(
    viewModel: DashboardVM,
    onLogout: () -> Unit
) {

    val totalMeals by viewModel.allMeals.collectAsState()
    val totalWorkouts by viewModel.allWorkouts.collectAsState()
    val caloIn by viewModel.calIn.collectAsState()
    val caloOut by viewModel.calOut.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "TỔNG QUAN HÔM NAY",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
            DashboardItem("Calo vào", caloIn)
            DashboardItem("Calo ra", caloOut)


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.logout(onLogout) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C8C8C))
        ) {
            Text("Đăng xuất")
        }
    }
}

@Composable
fun DashboardItem(title: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .clickable{},
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = value.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun rv99() {
    FitoTheme {
        val viewModel: DashboardVM = viewModel()
        Dashboard(
            viewModel,
            onLogout = {}
        )
    }
}