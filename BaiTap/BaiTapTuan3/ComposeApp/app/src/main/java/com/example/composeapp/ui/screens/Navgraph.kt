package com.example.composeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "ready") {
        composable("ready") { ReadyScreen(navController) }
        composable("components") { ComponentsScreen(navController) }
        composable("detail/{component}") { backStackEntry ->
            val component = backStackEntry.arguments?.getString("component")
            ComponentDetailScreen(component)
        }
    }
}