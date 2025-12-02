package com.example.fito

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fito.ui.theme.FitoTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*
import com.example.fito.components.BottomNavigationBar
import com.example.fito.viewmodel.ExerciseVM
import com.example.fito.viewmodel.MealVM
import com.example.fito.viewmodel.ScheduleVM

@Composable
fun ExitConfirmationDialogSC(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    viewModel: ScheduleVM
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
fun AddedScheduleDialog(
    viewModel: ScheduleVM,
    onDismiss: () -> Unit
) {
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
                    "Danh sách lịch đã thêm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp)
                ) {
                    items(viewModel.addedSchedules) { schedule ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text(schedule)
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
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
@Composable
fun ScheduleAddCard(viewModel: ScheduleVM) {
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
                modifier = Modifier
                    .padding(20.dp),

                ) {

                Text(
                    text = "Tạo lịch tập",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Tạo một lịch tập mà bạn bè có thể tham gia",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Chi tiết lịch tập",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = viewModel.scheduleDetail,
                    onValueChange = { viewModel.onScheduleDetailSet(it) },
                    label = { Text("Nhập thông tin lịch tập") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Thời gian",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = viewModel.scheduleTime,
                    onValueChange = { viewModel.onScheduleTimeChange(it) },
                    placeholder = { Text("Ví dụ: 4/12/2025") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Địa điểm",
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = viewModel.location,
                    onValueChange = { viewModel.onLocationSet(it) },
                    label = { Text("Nhập địa điểm") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { viewModel.addSchedule() },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8C8C8C),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text("Thêm lịch")
                    }

                    Button(
                        onClick = {
                            viewModel.onExitButtonClick()
                        },
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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleCalendar(
    schedules: Map<Int, List<String>>,
    selectedDay: Int?,
    onDaySelected: (Int) -> Unit
) {
    val today = LocalDate.now()
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val daysInMonth = currentMonth.lengthOfMonth()
    val monthName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale("vi"))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        IconButton(onClick = {
            currentMonth = currentMonth.minusMonths(1)
        }) {
            Text("←", fontSize = 32.sp)
        }

        Text(
            text = "${monthName.replaceFirstChar { it.uppercase() }} ${currentMonth.year}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = {
            currentMonth = currentMonth.plusMonths(1)
        }) {
            Text("→", fontSize = 32.sp)
        }
    }

    val weeks = (0 until daysInMonth).chunked(7)

    weeks.forEach { week ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            week.forEach { day ->
                val dayNumber = day + 1
                val isToday =
                    dayNumber == today.dayOfMonth &&
                            currentMonth.month == today.month &&
                            currentMonth.year == today.year

                val hasEvent = schedules.containsKey(dayNumber)

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .border(
                            width = 1.dp,
                            color = if (isToday) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (selectedDay == dayNumber) Color(0xFFB0E0E6)
                            else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { onDaySelected(dayNumber) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = dayNumber.toString(),
                            color = if (isToday) Color.Red else Color.Black
                        )

                        if (hasEvent) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(Color.Blue, shape = RoundedCornerShape(50))
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    selectedDay?.let { day ->
        val events = schedules[day]

        if (!events.isNullOrEmpty()) {
            Text(
                text = "Lịch ngày $day:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn {
                items(events.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(events[index])
                    }
                }
            }
        } else {
            Text(
                text = "Không có lịch cho ngày $day",
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ScheduleScreen(
    viewModel: ScheduleVM = viewModel()
) {
    val schedules = remember { mutableStateMapOf<Int, List<String>>() }
    var selectedDay by remember { mutableStateOf<Int?>(null) }
    Scaffold { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ScheduleCalendar(
                    schedules = schedules,
                    selectedDay = selectedDay,
                    onDaySelected = { selectedDay = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                        .clickable { viewModel.buttonClick() }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Thêm lịch tập",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp)
                        .clickable { viewModel.showAddedSchedulesDialog=true }
                    ,
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Xem lịch tập",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                if (viewModel.showAddedSchedulesDialog) {
                    AddedScheduleDialog(viewModel) {
                        viewModel.closeAddedSchedulesDialog()
                    }
                }
            }

            if (viewModel.showCard) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000)),
                    contentAlignment = Alignment.Center
                ) {
                    ScheduleAddCard(viewModel)
                }
            }

            if (viewModel.showExitDialog) {
                ExitConfirmationDialogSC(viewModel = viewModel,
                    onConfirm = { viewModel.confirmExit() },
                    onDismiss = { viewModel.declineExit() })
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun rv5() {
    FitoTheme {
        ScheduleScreen(viewModel())
    }
}
