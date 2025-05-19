package com.edgecasedesigns.androidsampleapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.edgecasedesigns.androidsampleapp.ui.screen.detail.DetailScreen
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController) }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: return@composable
            DetailScreen(navController = navController, itemId = id)
        }
    }
}