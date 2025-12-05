package com.example.fito

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fito.ui.theme.FitoTheme
import com.example.fito.components.BottomNavigationBar
import com.example.fito.data.local.database.FitoDatabase
import com.example.fito.data.repository.ExerciseRepository
import com.example.fito.viewmodel.DashboardVM
import com.example.fito.viewmodel.ExerciseVM
import com.example.fito.viewmodel.ExerciseVMFactory

data class PresetExercise(
    val name: String,
    val caloriesPer60: Int
)

val presetExercises = listOf(
    PresetExercise("Chạy bộ", 550),
    PresetExercise("Đạp xe", 600),
    PresetExercise("Nhảy dây", 600),
    PresetExercise("Yoga", 320),
    PresetExercise("Bơi lội", 625),
    PresetExercise("Leo cầu thang", 500),
    PresetExercise("Boxing", 650),
    PresetExercise("Burpee", 800),
    PresetExercise("HIIT", 800),
    PresetExercise("LISS", 450)
)

@Composable
fun PresetExerciseDropdown(viewModel: ExerciseVM,dashboardVM: DashboardVM) {

    Column(
        modifier=Modifier
            .padding(top=20.dp)
    ) {

        Box(
            modifier = Modifier
                .size(width=360.dp, height=60.dp)
                .height(60.dp)
                .background(Color(0xFF8C8C8C), RoundedCornerShape(14.dp))
                .clickable { viewModel.onDropdownClick() }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = if (viewModel.expanded)
                        "Ẩn danh sách bài tập"
                    else
                        "Danh sách bài tập có sẵn",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

        }

        if (viewModel.expanded) {
            Spacer(Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .size(width=360.dp, height=200.dp)
            ) {
                items(presetExercises) { exercise ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column {
                                Text(exercise.name, fontWeight = FontWeight.Bold)
                                Text(
                                    "${exercise.caloriesPer60} calo / 60 phút",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }

                            Button(
                                onClick = {
                                    viewModel.onExerciseChange(exercise.name)
                                    viewModel.onCaloriesChange(exercise.caloriesPer60.toString())
                                    viewModel.submitExercise()
                                }
                            ) {
                                Text("Thêm")
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun AddedExercisesDialog(
    viewModel: ExerciseVM,
    onDismiss: () -> Unit
) {
    val exercises by viewModel.todayExercises.collectAsState()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Danh sách bài tập đã thêm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))

                if (exercises.isEmpty()) {
                    Text("Chưa có bài tập nào được thêm.", color = Color.Gray)
                    Spacer(Modifier.height(16.dp))
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp)
                    ) {
                        items(exercises, key = { it.id ?: it.name }) { exercise ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(exercise.name, fontWeight = FontWeight.Bold)
                                    Text("${exercise.calories} calo", fontSize = 13.sp, color = Color.Gray)
                                }

                                Button(
                                    onClick = { viewModel.toggleExerciseCompleted(exercise) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (exercise.completed) Color(0xFF4CAF50) else Color(0xFFFF9800),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(if (exercise.completed) "Hoàn thành" else "Chưa hoàn thành")
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                }

                Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8C8C8C),
                        contentColor = Color.White
                    )
                ) {
                    Text("Đóng")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogExerciseCard(viewModel: ExerciseVM,dashboardVM: DashboardVM) {

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
                style = MaterialTheme.typography.titleMedium,
                fontSize=20.sp
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
                    label = { Text("Nhập một bài tập") },
                    modifier = Modifier
                        .fillMaxWidth()
                )


            Spacer(Modifier.height(6.dp))

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
    dashboardVM:DashboardVM=viewModel()
) {
    val context = LocalContext.current
    val database = FitoDatabase.getDatabase(context)
    val exerciseDao = database.exerciseDao()
    val repository = ExerciseRepository(exerciseDao)
    val factory = ExerciseVMFactory(repository)
    val viewModel: ExerciseVM = viewModel(factory = factory)


    Scaffold { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {

            item {
                PresetExerciseDropdown(viewModel, dashboardVM)
                Spacer(Modifier.height(16.dp))
            }

            item {
                LogExerciseCard(viewModel,dashboardVM)
                Spacer(Modifier.height(16.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .size(width=360.dp, height=60.dp)
                        .padding(horizontal = 16.dp)
                        .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                        .clickable { viewModel.onDialogOpen() }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Xem bài tập đã thêm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
        if (viewModel.showDialog) {
            AddedExercisesDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.onDialogClose() }
            )
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