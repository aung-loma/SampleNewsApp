package presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.just.simple.myapplication.model.Screen

@Composable
fun ComposeNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
        composable(Screen.NewsScreen.route) {
           NewsScreen(navController)
        }
        composable(Screen.DetailsScreen.route) {
           DetailsScreen(navController)
        }
    }

}