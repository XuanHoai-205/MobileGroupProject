package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.components.BottomNavigationBar
import com.example.fito.viewmodel.ExerciseVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogExerciseCard(viewModel: ExerciseVM) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {

            Text(
                text = "Ghi lại bài tập",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Theo dõi lượng calo đã được đốt cháy",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Loại bài tập",
                style = MaterialTheme.typography.bodyMedium
            )

                OutlinedTextField(
                    value = viewModel.selectedExercise,
                    onValueChange = {viewModel.onExerciseChange(it)},
                    readOnly = true,
                    label = { Text("Nhập một bài tập") },
                    modifier = Modifier
                        .fillMaxWidth()
                )


            Spacer(Modifier.height(6.dp))

            // Duration Label
            Text(
                text = "Thời gian(phút)",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = viewModel.duration,
                onValueChange = {viewModel.onDurationChange(it) },
                placeholder = { Text("Ví dụ: 30 phút") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Calo đốt cháy",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = viewModel.calories,
                onValueChange = { viewModel.onCaloriesChange(it) },
                placeholder = { Text("Ví dụ: 300 calo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))


            Button(
                onClick = {viewModel.submitExercise()},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8C8C8C),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(2.dp))
                Text("Thêm bài tập")
            }
        }
    }
}

@Composable
fun ExScreen(
    viewModel: ExerciseVM = androidx.lifecycle.viewmodel.compose.viewModel()
){
    Scaffold(
        bottomBar = { BottomNavigationBar(
            currentRoute = "exercise",
            onItemClick = {}
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            LogExerciseCard(viewModel)
            Box(
                modifier = Modifier

                    .height(60.dp)
                    .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                    .clickable {
                        // ví dụ: navController.navigate("schedule_detail")
                    }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Xem bài tập đã thêm",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color=Color.White
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun rv6() {
    FitoTheme {
        ExScreen()
    }
}