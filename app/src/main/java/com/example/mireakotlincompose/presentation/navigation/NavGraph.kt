package com.example.mireakotlincompose.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mireakotlincompose.presentation.camera_screen.CameraScreen
import com.example.mireakotlincompose.presentation.list_screen.ListScreen

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object CameraScreen : Screen("camera_screen", "Камера", Icons.Default.Add)
    data object ListScreen : Screen("list_screen", "Список", Icons.AutoMirrored.Filled.List)
}


@Composable
fun NavGraph(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CameraScreen.route,
        modifier = Modifier.padding(innerPadding)
    ){
        composable(Screen.CameraScreen.route){
            CameraScreen(navController = navHostController)
        }
        composable(Screen.ListScreen.route) {
            ListScreen(navHostController = navHostController)
        }
    }
}