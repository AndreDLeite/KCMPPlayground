package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import e_commerce.presentation.product_home.components.ProductsListComponent

@Composable
fun ProductListScreenRoot(
    state: ProductHomeState,
    bottomPadding: Dp = 0.dp,
    onAction: (ProductHomeAction) -> Unit
) {
    ProductListScreen(state, bottomPadding) { action ->
        onAction(action)
    }
}

@Composable
fun ProductListScreen(
    state: ProductHomeState,
    bottomPadding: Dp,
    onAction: (ProductHomeAction) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPadding)
    ) {
        ProductsListComponent(
            products = state.products,
            onProductClick = { productId ->
                onAction(ProductHomeAction.OnProductClick(productId))
            }
        )
    }
}