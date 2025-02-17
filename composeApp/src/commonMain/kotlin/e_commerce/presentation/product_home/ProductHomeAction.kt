package e_commerce.presentation.product_home

sealed interface ProductHomeAction {
    data class OnSearchQueryChange(val query: String) : ProductHomeAction
    data class OnProductClick(val productId: String) : ProductHomeAction
    data class OnTabSelected(val index: Int) : ProductHomeAction
    data object OnDrawerClick : ProductHomeAction
    data object OnFavoritesClick : ProductHomeAction
    data object OnShoppingCartClick : ProductHomeAction
    data object OnRetryClick : ProductHomeAction
    data object OnSettingsClick: ProductHomeAction
    data object OnProfileClick: ProductHomeAction
    data object OnNotificationsClick: ProductHomeAction
}