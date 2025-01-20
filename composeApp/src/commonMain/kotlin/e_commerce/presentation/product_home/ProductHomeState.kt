package e_commerce.presentation.product_home

import e_commerce.domain.models.Product

data class ProductHomeState(
    val isLoading: Boolean = true,
    val userName: String = "Andre Leite",
    val errorMessage: String = String(),
    val shouldOpenDrawer: Boolean = false,
    val products: List<Product> = emptyList(),
    val searchQuery: String = String(),
    val selectedTabIndex: Int = 0,
)
