package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fito.data.repository.ScheduleRepository
import com.example.fito.data.model.ScheduleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeParseException
import java.time.DateTimeException

class ScheduleVM(private val repository: ScheduleRepository) : ViewModel() {

    val allSchedules: StateFlow<List<ScheduleEntity>> = repository.getAllSchedules().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _currentCalendarMonth = MutableStateFlow(YearMonth.now())
    val currentCalendarMonth: StateFlow<YearMonth> = _currentCalendarMonth

    fun updateCurrentCalendarMonth(yearMonth: YearMonth) {
        _currentCalendarMonth.value = yearMonth
    }


    val schedulesForCalendar: StateFlow<Map<Int, List<String>>> = combine(
        allSchedules,
        _currentCalendarMonth
    ) { schedules, yearMonth ->
        schedules
            .filter { it.scheduleMonth == yearMonth.monthValue && it.scheduleYear == yearMonth.year }
            .groupBy { it.scheduleDay }
            .mapValues { entry ->
                entry.value.sortedBy { it.startTime }.map { "${it.startTime} - ${it.title}" }
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    val addedSchedules: StateFlow<List<String>> = allSchedules
        .map { schedules ->
            schedules.map { "${it.startTime} (${it.scheduleDay}/${it.scheduleMonth}/${it.scheduleYear}) tại ${it.location}: ${it.exercisesSummary}" }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    var scheduleDayInput by mutableStateOf("")
        private set
    var scheduleMonthInput by mutableStateOf("")
        private set
    var scheduleYearInput by mutableStateOf("")
        private set
    var scheduleTimeInput by mutableStateOf("")
        private set

    var scheduleDetail by mutableStateOf("")
        private set

    var showCard by mutableStateOf(false)
        private set

    var location by mutableStateOf("")
        private set

    var showAddedSchedulesDialog by mutableStateOf(false)

    fun buttonClick() {
        showCard = true
        _errorMessage.value = null
    }

    var showExitDialog by mutableStateOf(false)
        private set

    fun onExitButtonClick() {
        showExitDialog = true
    }

    fun confirmExit() {
        showCard = false
        showExitDialog = false
        scheduleDetail = ""
        location = ""
        scheduleDayInput = ""
        scheduleMonthInput = ""
        scheduleYearInput = ""
        scheduleTimeInput = ""
        _errorMessage.value = null
    }

    fun declineExit() {
        showExitDialog = false
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun onScheduleDetailSet(value: String) {
        scheduleDetail = value
    }

    fun onScheduleDayChange(value: String) {
        scheduleDayInput = value
    }
    fun onScheduleMonthChange(value: String) {
        scheduleMonthInput = value
    }
    fun onScheduleYearChange(value: String) {
        scheduleYearInput = value
    }
    fun onScheduleTimeChange(value: String) {
        scheduleTimeInput = value
    }

    fun onLocationSet(value: String){
        location = value
    }

    fun closeAddedSchedulesDialog(){
        showAddedSchedulesDialog=false
    }

    fun addSchedule() {
        val day = scheduleDayInput.toIntOrNull()
        val month = scheduleMonthInput.toIntOrNull()
        val year = scheduleYearInput.toIntOrNull()
        val time = scheduleTimeInput.trim()

        if (scheduleDetail.isNotEmpty() && location.isNotEmpty() && day != null && month != null && year != null && time.isNotEmpty()) {

            try {
                LocalDateTime.of(year, month, day, 0, 0)
                DateTimeFormatter.ofPattern("HH:mm").parse(time)

                viewModelScope.launch {
                    val newSchedule = ScheduleEntity(
                        title = "Lịch tập tại $location",
                        scheduleDay = day,
                        scheduleMonth = month,
                        scheduleYear = year,
                        startTime = time,
                        location = location,
                        status = "Đã lên lịch",
                        exercisesSummary = scheduleDetail
                    )

                    repository.insertSchedule(newSchedule)
                    _errorMessage.value = "Thêm lịch thành công!"

                    val addedMonth = YearMonth.of(year, month)
                    if (addedMonth != _currentCalendarMonth.value) {
                        _currentCalendarMonth.value = addedMonth
                    }

                    confirmExit()
                }
            } catch (e: DateTimeParseException) {
                _errorMessage.value = "Lỗi: Định dạng giờ không hợp lệ (cần HH:mm)."
            } catch (e: DateTimeException) {
                _errorMessage.value = "Lỗi: Ngày tháng không hợp lệ (ví dụ: ngày 30/2, hoặc tháng > 12)."
            } catch (e: Exception) {
                _errorMessage.value = "Lỗi chung khi tạo lịch: ${e.message}"
            }
        } else {
            _errorMessage.value = "Lỗi: Vui lòng điền đầy đủ và đúng định dạng các trường."
        }
    }
}