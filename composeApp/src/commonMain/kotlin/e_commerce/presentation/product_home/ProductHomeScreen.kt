package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.EmptyContentComponent
import core.presentation.LoadingScreen
import e_commerce.presentation.product_home.components.HomeTabComponent
import e_commerce.presentation.product_home.components.HomeTopBar
import e_commerce.presentation.product_home.components.ProductsHomeNavigationDrawer
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_clear_24
import kmpplayground.composeapp.generated.resources.baseline_shopping_cart_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductHomeScreenRoot(
    viewModel: ProductHomeViewModel,
    onFavoritesClick: () -> Unit,
    onShoppingCartClick: () -> Unit,
    onProductClick: (productId: String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductHomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ProductHomeAction.OnFavoritesClick -> onFavoritesClick()
                is ProductHomeAction.OnShoppingCartClick -> onShoppingCartClick()
                is ProductHomeAction.OnProductClick -> onProductClick(action.productId)
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
                    userName = state.userName,
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
                HomeTabComponent(
                    state = state,
                    onAction = { action ->
                        onAction(action)
                    },
                    productsTabContent = {
                        when {
                            state.isLoading -> {
                                LoadingScreen()
                            }

                            state.errorMessage != null -> {
                                EmptyContentComponent(
                                    painter = painterResource(Res.drawable.baseline_clear_24),
                                    message = "Ops, something went wrong...",
                                    withRetryButton = true,
                                ) {
                                    onAction(ProductHomeAction.OnRetryClick)
                                }
                            }

                            state.products.isEmpty() -> {
                                EmptyContentComponent(
                                    painter = painterResource(Res.drawable.baseline_shopping_cart_24),
                                    message = "No products found...",
                                    withRetryButton = true,
                                )
                            }

                            else -> {
                                ProductListScreenRoot(state) { action ->
                                    onAction(action)
                                }
                            }
                        }

                    },
                    mapsTabContent = {
                        //ProductMapsScreen()
                    }
                )
            }
        }
    }

}