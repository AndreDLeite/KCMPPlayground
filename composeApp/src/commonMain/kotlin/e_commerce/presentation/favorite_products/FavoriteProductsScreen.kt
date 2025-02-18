package e_commerce.presentation.favorite_products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.desing_system.EmptyContentComponent
import core.presentation.desing_system.TopBarComponent
import e_commerce.presentation.product_home.components.ProductsListComponent
import e_commerce.presentation.product_home.components.ProductsSearchBar
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_heart_broken_24
import kmpplayground.composeapp.generated.resources.empty_product_favorites_title
import kmpplayground.composeapp.generated.resources.product_favorites_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavoriteProductsScreenRoot(
    viewModel: FavoriteProductsViewModel,
    onGoBackClick: () -> Unit,
    onProductClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FavoriteProductsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is FavoriteProductsAction.OnGoBackClick       -> onGoBackClick()
                is FavoriteProductsAction.OnProductClick      -> onProductClick(action.productId)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
fun FavoriteProductsScreen(
    state: FavoriteProductsState,
    onAction: (FavoriteProductsAction) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopBarComponent(
                middleText = stringResource(Res.string.product_favorites_title),
            ) {
                onAction(FavoriteProductsAction.OnGoBackClick)
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                )
        ) {
            ProductsSearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryChange = { query ->
                    onAction(FavoriteProductsAction.OnSearchQueryChange(query))
                },
                onImeSearch = {
                    keyboardController?.hide()
                },
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (state.favoriteProducts.isEmpty()) {
                EmptyContentComponent(
                    painter = painterResource(Res.drawable.baseline_heart_broken_24),
                    painterColor = MaterialTheme.colorScheme.onBackground,
                    message = stringResource(Res.string.empty_product_favorites_title)
                )
            } else {
                ProductsListComponent(
                    products = state.favoriteProducts,
                ) { product ->
                    onAction(FavoriteProductsAction.OnProductClick(product))
                }
            }
        }
    }
}
