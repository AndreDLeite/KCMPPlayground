package app.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
    data object ECommerceSplash : Screens("ecommerce_splash")
    data object ECommerceHome : Screens("ecommerce_home")
    data object ECommerceFavorites : Screens("ecommerce_favorites")
    data object ECommerceCart : Screens("ecommerce_favorites")
}