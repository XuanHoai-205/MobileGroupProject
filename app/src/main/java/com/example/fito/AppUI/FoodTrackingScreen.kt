package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fito.data.local.database.FitoDatabase
import com.example.fito.data.model.MealEntity
import com.example.fito.data.repository.MealRepository
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.viewmodel.DashboardVM
import com.example.fito.viewmodel.MealVM
import com.example.fito.viewmodel.MealVMFactory
import java.util.Calendar

@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    viewModel: MealVM
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Xác nhận thoát") },
        text = { Text("Các thông tin về lịch bạn đã điền sẽ không được lưu lại. Bạn có chắc chắn muốn thoát?") },
        confirmButton = {
            TextButton(onClick = { viewModel.confirmExit() }) {
                Text("Thoát")
            }
        },
        dismissButton = {
            TextButton(onClick = { viewModel.declineExit() }) {
                Text("Quay lại")
            }
        }
    )
}
@Composable
fun FoodAddCard(viewModel: MealVM,
                dashboardViewModel: DashboardVM) {
    if (viewModel.showCard) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset (y = -60.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Ghi lại bữa ăn",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Theo dõi lượng calo bạn đã nạp vào ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Món ăn",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = viewModel.mealDetail,
                    onValueChange = { viewModel.onMealDetailSet(it) },
                    label = { Text("Nhập món ăn") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Calo tiêu thụ",
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { viewModel.submitMeal() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8C8C8C),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text("Thêm bữa ăn")
                    }

                    Button(
                        onClick = {
                            viewModel.onExitButtonClick()  },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Thoát")
                    }
                }
            }
        }
    }
}
@Composable
fun MealItem(meal: MealEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(meal.mealName, fontWeight = FontWeight.Bold)
            Text(
                "${meal.mealCalories} calo",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Text(
                "Buổi: ${meal.mealType}",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
    fun MealButton(text: String,viewModel: MealVM, mealType: String) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { viewModel.buttonClick()
                    viewModel.onMealTypeSelect(mealType)
                    },
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
fun MealButtonsSection(viewModel: MealVM) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MealButton("Thêm bữa sáng",viewModel, "Sáng")
        MealButton("Thêm bữa trưa",viewModel,"Trưa")
        MealButton("Thêm bữa tối",viewModel,"Tối")
        MealButton("Thêm các bữa phụ",viewModel,"Phụ")
    }
}

@Composable
fun FoodTrackScreen() {
    val context = LocalContext.current
    val dashboardVM: DashboardVM = viewModel()
    val database = FitoDatabase.getDatabase(context)
    val mealDao = database.mealDao()
    val repository = MealRepository(mealDao)
    val factory = MealVMFactory(repository)

    val mealVM: MealVM = viewModel(factory = factory)



    val startOfToday = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val endOfToday = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }.timeInMillis
    val meals by mealVM.getTodayMeals(
        startOfDay = startOfToday,
        endOfDay = endOfToday
    ).collectAsState(initial = emptyList())
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MealButtonsSection(viewModel = mealVM)
            Spacer(modifier = Modifier.height(20.dp))

            if (meals.isEmpty()) {
                Text("Chưa có bữa ăn nào được ghi lại.", color = Color.Gray)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(meals) { meal ->
                        MealItem(meal)
                    }
                }
            }
        }

        if (mealVM.showCard) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
                    .clickable { mealVM.closeCard() },
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.clickable(false) {}) {
                    FoodAddCard(viewModel = mealVM, dashboardViewModel = dashboardVM)
                }
            }
        }

        if (mealVM.showExitDialog) {
            ExitConfirmationDialog(
                viewModel = mealVM,
                onConfirm = { mealVM.confirmExit() },
                onDismiss = { mealVM.declineExit() }
            )
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