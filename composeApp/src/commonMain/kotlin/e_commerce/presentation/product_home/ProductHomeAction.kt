package e_commerce.presentation.product_home

sealed interface ProductHomeAction {
    data class OnSearchQueryChange(val query: String) : ProductHomeAction
    data class OnProductClick(val productId: String) : ProductHomeAction
    data class OnTabSelected(val index: Int) : ProductHomeAction
    data object OnDrawerClick : ProductHomeAction
    data object OnFavoritesClick : ProductHomeAction
    data object OnShoppingCartClick : ProductHomeAction
}