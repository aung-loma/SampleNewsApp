package co.just.simple.myapplication.model

sealed class Screen(val route: String) {

    object NewsScreen : Screen("news")

    object DetailsScreen : Screen("news_details")

}