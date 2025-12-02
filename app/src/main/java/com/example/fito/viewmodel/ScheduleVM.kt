package com.example.fito.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ScheduleVM : ViewModel() {

    var addedSchedules by mutableStateOf(listOf<String>())
        private set
    var scheduleDetail by mutableStateOf("")
        private set

    var scheduleTime by mutableStateOf("")
        private set

    var showCard by mutableStateOf(false)
        private set

    var location by mutableStateOf("")
        private set

    var showAddedSchedulesDialog by mutableStateOf(false)


    fun buttonClick() {
        showCard = true
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
        scheduleTime = ""
    }

    fun declineExit() {
        showExitDialog = false
    }

    fun onScheduleDetailSet(value: String) {
        scheduleDetail = value
    }

    fun onScheduleTimeChange(value: String) {
        scheduleTime = value
    }
    fun onLocationSet(value: String){
        location = value
    }


    fun closeAddedSchedulesDialog(){
        showAddedSchedulesDialog=false
    }
    fun addSchedule() {
        if (scheduleDetail.isNotEmpty() && scheduleTime.isNotEmpty() && location.isNotEmpty()) {
            val newSchedule = "$scheduleDetail - $scheduleTime - $location"
            addedSchedules = addedSchedules + newSchedule

            scheduleDetail = ""
            scheduleTime = ""
            location = ""
            showCard = false
        }
    }
}
