package com.example.fito

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.fito.ui.theme.FitoTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*
import com.example.fito.components.BottomNavigationBar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen() {

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->

        val schedules = remember { mutableStateMapOf<Int, MutableList<String>>() }
        var selectedDay by remember { mutableStateOf<Int?>(null) }

        val today = LocalDate.now()
        val currentMonth = YearMonth.now()
        val daysInMonth = currentMonth.lengthOfMonth()
        val monthName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale("vi"))

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "${monthName.replaceFirstChar { it.uppercase() }} ${currentMonth.year}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lưới ngày (7 cột)
            val weeks = (0 until daysInMonth).chunked(7)
            weeks.forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    week.forEach { day ->
                        val dayNumber = day + 1
                        val isToday = dayNumber == today.dayOfMonth
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
                                .clickable {
                                    selectedDay = dayNumber
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = dayNumber.toString(),
                                    color = if (isToday) Color.Red else Color.Black
                                )
                                // Khi sau này có sự kiện, sẽ hiển thị dấu tại đây
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

            // Hiển thị chi tiết lịch khi chọn ngày (nếu có)
            selectedDay?.let { day ->
                val events = schedules[day]
                if (events != null && events.isNotEmpty()) {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                    .clickable {
                        // TODO: Mở màn hình thêm lịch
                        // ví dụ: navController.navigate("add_schedule")
                    }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Thêm lịch tập",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color=Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF8C8C8C), shape = RoundedCornerShape(12.dp))
                    .clickable {
                        // TODO: Mở trang xem lịch tập
                        // ví dụ: navController.navigate("schedule_detail")
                    }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Xem lịch tập",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color=Color.White
                )
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun rv5() {
    FitoTheme {
        ScheduleScreen()
    }
}
