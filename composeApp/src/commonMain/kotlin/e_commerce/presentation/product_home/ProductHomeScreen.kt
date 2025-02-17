package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.EmptyContentComponent
import core.presentation.LoadingScreen
import e_commerce.presentation.product_home.components.HomeTabComponent
import e_commerce.presentation.product_home.components.HomeTopBar
import e_commerce.presentation.product_home.components.ProductsHomeNavigationDrawer
import e_commerce.presentation.product_home.components.ProductsSearchBar
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_clear_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductHomeScreenRoot(
    viewModel: ProductHomeViewModel,
    onFavoritesClick: () -> Unit,
    onShoppingCartClick: () -> Unit,
    onProductClick: (productId: String) -> Unit,
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onNotificationsClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductHomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ProductHomeAction.OnFavoritesClick     -> onFavoritesClick()
                is ProductHomeAction.OnShoppingCartClick  -> onShoppingCartClick()
                is ProductHomeAction.OnProductClick       -> onProductClick(action.productId)
                is ProductHomeAction.OnSettingsClick      -> onSettingsClick()
                is ProductHomeAction.OnProfileClick       -> onProfileClick()
                is ProductHomeAction.OnNotificationsClick -> onNotificationsClick()
                else                                      -> Unit
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
    val keyboardController = LocalSoftwareKeyboardController.current

    ProductsHomeNavigationDrawer(
        shouldOpenDrawer = state.shouldOpenDrawer,
        onDrawerCloseClick = {
            onAction(ProductHomeAction.OnDrawerClick)
        },
        onSettingsClick = {
            onAction(ProductHomeAction.OnSettingsClick)
        },
        onProfileClick = {
            onAction(ProductHomeAction.OnProfileClick)
        },
        onNotificationClick = {
            onAction(ProductHomeAction.OnNotificationsClick)
        }
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
                    onDrawerClick = {
                        onAction(ProductHomeAction.OnDrawerClick)
                    }
                )
            },
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                HomeTabComponent(
                    state = state,
                    onAction = { action ->
                        onAction(action)
                    },
                    productsTabContent = {
                        Column(
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

                            when {
                                state.isLoading            -> {
                                    LoadingScreen()
                                }

                                state.errorMessage != null -> {
                                    EmptyContentComponent(
                                        painter = painterResource(Res.drawable.baseline_clear_24),
                                        message = state.errorMessage.asString(),
                                        withRetryButton = true,
                                    ) {
                                        onAction(ProductHomeAction.OnRetryClick)
                                    }
                                }

                                state.products.isEmpty()   -> {
                                    EmptyContentComponent(
                                        painter = painterResource(Res.drawable.baseline_clear_24),
                                        message = "No products found...",
                                    )
                                }

                                else                       -> {
                                    ProductListScreenRoot(
                                        state,
                                        bottomPadding = innerPadding.calculateBottomPadding()
                                    ) { action ->
                                        onAction(action)
                                    }
                                }
                            }
                        }
                    },
                    mapsTabContent = {
                        MapComponent(
                            state = state,
                            bottomPadding = innerPadding.calculateBottomPadding(),
                        ) { productId ->
                            onAction(ProductHomeAction.OnProductClick(productId))
                        }
                    }
                )
            }
        }
    }

}