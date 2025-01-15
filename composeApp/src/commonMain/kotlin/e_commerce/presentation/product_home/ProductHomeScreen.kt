package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import e_commerce.presentation.product_home.components.HomeTopBar
import e_commerce.presentation.product_home.components.ProductsHomeNavigationDrawer

@Composable
fun ProductHomeScreenRoot(
    viewModel: ProductHomeViewModel,
    onFavoritesClick: () -> Unit,
    onShoppingCartClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductHomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ProductHomeAction.OnFavoritesClick -> onFavoritesClick()
                is ProductHomeAction.OnShoppingCartClick -> onShoppingCartClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )

}

@Composable
fun ProductHomeScreen(
    state: ProductHomeState,
    onAction: (ProductHomeAction) -> Unit,
) {

    ProductsHomeNavigationDrawer(
        shouldOpenDrawer = state.shouldOpenDrawer,
        onDrawerCloseClick = {
            onAction(ProductHomeAction.OnDrawerClick)
        },
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                HomeTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    onFavoritesClick = {
                        onAction(ProductHomeAction.OnFavoritesClick)
                    },
                    onShoppingCartClick = {
                        onAction(ProductHomeAction.OnShoppingCartClick)
                    },
                    onProfileIconClick = {
                        onAction(ProductHomeAction.OnDrawerClick)
                    }
                )
            },
        ) {
            Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
                //HomeTabRow(navigator = navGraphNavigator)
            }
        }
    }

}