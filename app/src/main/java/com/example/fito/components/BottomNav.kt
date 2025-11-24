package com.example.fito.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        BottomNavItem("", Icons.Filled.Home),
        BottomNavItem("", Icons.Filled.LocalDining),
        BottomNavItem("", Icons.Filled.FitnessCenter),
        BottomNavItem("", Icons.Filled.Group),
        BottomNavItem("", Icons.Filled.CalendarToday)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = false,
                onClick = { /* TODO: handle click */ },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}


