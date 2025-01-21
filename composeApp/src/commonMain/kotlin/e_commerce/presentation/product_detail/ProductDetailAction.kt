package e_commerce.presentation.product_detail

import e_commerce.domain.models.Product

sealed interface ProductDetailAction {
    data object OnRetryClick : ProductDetailAction
    data object OnGoBackClick : ProductDetailAction
    data class OnAddToFavoritesClick(val product: Product): ProductDetailAction
}