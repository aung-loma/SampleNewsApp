package presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.just.simple.myapplication.model.NavigationItem
import co.just.simple.myapplication.presentation.NewsScreen
import co.just.simple.myapplication.presentation.ProfileDetailScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
                      navController: NavHostController,
                      startDestination: String = NavigationItem.News.route) {

    NavHost(modifier = modifier,navController = navController, startDestination = startDestination) {
        composable(NavigationItem.News.route) {
           NewsScreen(navController)
        }
        composable(NavigationItem.ProfileDetail.route) {
           ProfileDetailScreen(navController)
        }
    }

}