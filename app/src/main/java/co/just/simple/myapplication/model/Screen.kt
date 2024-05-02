package co.just.simple.myapplication.model

enum class Screen {
    NEWS,
    PROFILE_DETAIL,
    EDIT_PUBLISHER,
}
sealed class NavigationItem(val route: String) {
    object News : NavigationItem(Screen.NEWS.name)
    object ProfileDetail : NavigationItem(Screen.PROFILE_DETAIL.name)
    object EditPublisher : NavigationItem(Screen.EDIT_PUBLISHER.name)
}