package com.example.fito

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fito.components.BottomNavigationBar
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.viewmodel.ExerciseVM
import com.example.fito.viewmodel.MealVM


@Composable
fun FoodAddCard(viewModel: MealVM){
    var mealDetail by remember { mutableStateOf("") }
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
                    value = viewModel.mealDetail,
                    onValueChange = {viewModel.onMealDetailSet(it)},
                    readOnly = true,
                    label = { Text("Nhập một bài tập") },
                    modifier = Modifier
                        .fillMaxWidth()
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
                    onClick = {viewModel.submitMeal()},
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
                    Text("Thêm bữa ăn")
                }
            }
        }
    }


@Composable
fun MealButton(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {  },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF8C8C8C)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}
@Composable
fun MealButtonsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MealButton("Thêm bữa sáng")
        MealButton("Thêm bữa trưa")
        MealButton("Thêm bữa tối")
        MealButton("Thêm các bữa phụ")
    }
}
@Composable
fun FoodTrackScreen(){
    Scaffold(
        bottomBar = { BottomNavigationBar(
            currentRoute = "food_tracking",
            onItemClick = {}
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MealButtonsSection()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun rv7() {
    FitoTheme {
        FoodTrackScreen()
    }
}