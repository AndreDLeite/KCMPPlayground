package e_commerce.presentation.product_home

import core.presentation.UiText
import e_commerce.domain.models.Product

data class ProductHomeState(
    val isLoading: Boolean = true,
    val userName: String = "Andre Leite",
    val errorMessage: UiText? = null,
    val shouldOpenDrawer: Boolean = false,
    val products: List<Product> = emptyList(),
    val searchQuery: String = String(),
    val selectedTabIndex: Int = 0,
) {
    val isError: Boolean
        get() = errorMessage != null

    val hasNoProduct: Boolean
        get() = products.isEmpty()

}
