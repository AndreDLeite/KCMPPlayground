package e_commerce.presentation.product_detail

import core.presentation.UiText
import e_commerce.domain.models.Product

data class ProductDetailState(
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val currentProduct: Product? = null,
)