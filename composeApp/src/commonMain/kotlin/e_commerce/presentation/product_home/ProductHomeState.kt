package e_commerce.presentation.product_home

data class ProductHomeState(
    val isLoading: Boolean = true,
    val errorMessage: String = String(),
    val shouldOpenDrawer: Boolean = false,
    val products: List<String> = emptyList(),
)
