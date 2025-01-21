package e_commerce.presentation.product_detail

sealed interface ProductDetailAction {
    data object OnRetryClick : ProductDetailAction
    data object OnGoBackClick : ProductDetailAction
    data class OnAddToFavoritesClick(val productId: String): ProductDetailAction
}