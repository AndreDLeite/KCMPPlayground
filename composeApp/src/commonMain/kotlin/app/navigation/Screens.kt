package app.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object ProjectsHub : Screens

    @Serializable
    data object ProjectsHubHome : Screens

    @Serializable
    data object ECommerceGraph : Screens

    @Serializable
    data object ECommerceSplash : Screens

    @Serializable
    data object ECommerceHome : Screens

    @Serializable
    data object ECommerceFavorites : Screens

    @Serializable
    data object ECommerceCart : Screens

    @Serializable
    data class ECommerceProductDetails(val productId: String) : Screens
}