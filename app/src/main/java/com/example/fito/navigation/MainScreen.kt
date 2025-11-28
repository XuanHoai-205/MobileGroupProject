package com.example.fito.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fito.AppUI.FriendCircle
import com.example.fito.DashboardScreen
import com.example.fito.ExScreen
import com.example.fito.FoodTrackScreen
import com.example.fito.ScheduleScreen
import com.example.fito.components.BottomNavigationBar


@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Exercise.route) { ExScreen() }
            composable(Screen.FoodTracking.route) { FoodTrackScreen() }
            composable(Screen.Social.route) { FriendCircle() }
            composable(Screen.WorkoutSchedule.route) { ScheduleScreen() }
        }
    }
}



