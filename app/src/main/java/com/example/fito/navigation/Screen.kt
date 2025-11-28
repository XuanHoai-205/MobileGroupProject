
package com.example.fito.navigation
sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
    object Exercise : Screen("exercise")
    object FoodTracking : Screen("food_tracking")
    object Social : Screen("social")
    object WorkoutSchedule : Screen("workout_schedule")
}