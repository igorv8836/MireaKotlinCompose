package com.example.mireakotlincompose.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mireakotlincompose.presentation.camera_screen.CameraScreen
import com.example.mireakotlincompose.presentation.facts_screen.FactsScreen
import com.example.mireakotlincompose.presentation.list_screen.ListScreen
import com.example.mireakotlincompose.presentation.photo_screen.PhotoScreen

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object CameraScreen : Screen("camera_screen", "Камера", Icons.Default.Add)
    data object ListScreen : Screen("list_screen", "Список", Icons.AutoMirrored.Filled.List)
    data object FactsScreen : Screen("cat_facts_screen", "Факты", Icons.Default.Email)
    data object PhotoScreen : Screen("photo_screen", "Фото", Icons.Default.Star)
}


@Composable
fun NavGraph(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FactsScreen.route,
        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
    ){
        composable(Screen.CameraScreen.route){
            CameraScreen(navController = navHostController)
        }
        composable(Screen.ListScreen.route) {
            ListScreen(navHostController = navHostController)
        }
        composable(Screen.FactsScreen.route) {
            FactsScreen(navHostController = navHostController)
        }
        composable(Screen.PhotoScreen.route) {
            PhotoScreen()
        }
    }
}