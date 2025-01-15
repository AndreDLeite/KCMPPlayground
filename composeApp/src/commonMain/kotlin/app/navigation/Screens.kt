package app.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
}