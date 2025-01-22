package e_commerce.presentation.product_home

import androidx.compose.runtime.Composable

@Composable
expect fun MapComponent(
    state: ProductHomeState,
    onProductClick: (String) -> Unit,
)