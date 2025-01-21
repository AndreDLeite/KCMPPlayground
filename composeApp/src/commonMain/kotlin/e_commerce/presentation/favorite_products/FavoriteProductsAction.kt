package e_commerce.presentation.favorite_products

sealed interface FavoriteProductsAction {
    data object OnGoBackClick : FavoriteProductsAction
    data class OnSearchQueryChange(val query: String) : FavoriteProductsAction
    data class OnProductClick(val productId: String) : FavoriteProductsAction
}