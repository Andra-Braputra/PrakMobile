package com.example.scrollablecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.Menu
            ) {
                composable(Routes.Menu) {
                    FalloutApp(navController)
                }
                composable(
                    route = "detail/{gameId}",
                    arguments = listOf(navArgument("gameId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
                    ShowDetail(gameId = gameId)
                }
            }
        }
    }
}
