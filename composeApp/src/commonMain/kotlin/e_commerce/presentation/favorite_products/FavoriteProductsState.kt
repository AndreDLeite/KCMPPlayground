package e_commerce.presentation.favorite_products

import e_commerce.domain.models.Product

data class FavoriteProductsState(
    val searchQuery: String = "",
    val favoriteProducts: List<Product> = emptyList(),
)
