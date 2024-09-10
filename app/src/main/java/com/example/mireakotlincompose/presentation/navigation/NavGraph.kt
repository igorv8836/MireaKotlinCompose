package com.example.mireakotlincompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mireakotlincompose.presentation.camera_screen.CameraScreen
import com.example.mireakotlincompose.presentation.list_screen.ListScreen

sealed class Screen(val route: String) {
    data object CameraScreen : Screen("camera")
    data object ListScreen : Screen("list")
}


@Composable
fun NavGraph() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.CameraScreen.route){
        composable(Screen.CameraScreen.route){
            CameraScreen(navController = navHostController)
        }
        composable(Screen.ListScreen.route) {
            ListScreen(navHostController = navHostController)
        }
    }
}