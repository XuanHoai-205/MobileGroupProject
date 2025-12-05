package com.example.fito.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fito.AppUI.FriendCircle
import com.example.fito.ExScreen
import com.example.fito.FoodTrackScreen
import com.example.fito.ScheduleScreen
import com.example.fito.components.BottomNavigationBar
import com.example.fito.ui.Dashboard
import com.example.fito.viewmodel.DashboardVM


@Composable
fun MainScreen(onLogOut: () -> Unit) {
    val bottomNavController = rememberNavController()

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemClick = { route ->
                    bottomNavController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                val dashboardViewModel: DashboardVM = viewModel()

                Dashboard(
                    viewModel = dashboardViewModel,
                    onLogout = onLogOut
                )}
            composable(Screen.Exercise.route) { ExScreen() }
            composable(Screen.FoodTracking.route) { FoodTrackScreen() }
            composable(Screen.Social.route) { FriendCircle() }
            composable(Screen.WorkoutSchedule.route) { ScheduleScreen() }
        }
    }
}




