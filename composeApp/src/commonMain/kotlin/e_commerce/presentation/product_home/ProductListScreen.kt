package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import e_commerce.presentation.product_home.components.ProductsListComponent
import e_commerce.presentation.product_home.components.ProductsSearchBar

@Composable
fun ProductListScreenRoot(
    state: ProductHomeState,
    onAction: (ProductHomeAction) -> Unit
) {
    ProductListScreen(state) { action ->
        onAction(action)
    }
}

@Composable
fun ProductListScreen(
    state: ProductHomeState,
    onAction: (ProductHomeAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProductsSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { query ->
                onAction(ProductHomeAction.OnSearchQueryChange(query))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        ProductsListComponent(
            products = state.products,
            onProductClick = { productId ->
                onAction(ProductHomeAction.OnProductClick(productId))
            }
        )
    }
}