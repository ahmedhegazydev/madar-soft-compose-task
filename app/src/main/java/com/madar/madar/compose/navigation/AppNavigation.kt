package com.madar.madar.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madar.madar.UserInputScreen
import com.madar.madar.compose.components.UserListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "userInput") {
        composable("userInput") { UserInputScreen(navController) }
        composable("userList") { UserListScreen() }
    }
}