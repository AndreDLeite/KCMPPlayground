package e_commerce.presentation.product_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.desing_system.EmptyContentComponent
import core.presentation.desing_system.LoadingScreen
import e_commerce.presentation.product_detail.components.ProductDetailsBottomComponent
import e_commerce.presentation.product_detail.components.ProductDetailsInfo
import e_commerce.presentation.product_detail.components.ProductDetailsMovingBackground
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_clear_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductDetailsScreenRoot(
    viewModel: ProductDetailsViewModel,
    onGoBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProductDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ProductDetailAction.OnGoBackClick -> onGoBackClick()
                else                                 -> viewModel.onAction(action)
            }
        }
    )

}

@Composable
fun ProductDetailsScreen(
    state: ProductDetailState,
    onAction: (ProductDetailAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        bottomBar = {
            state.currentProduct?.let {
                ProductDetailsBottomComponent(
                    quantity = state.addToCartQuantity,
                    currentProduct = it,
                    onAction = { action ->
                        onAction(action)
                    }
                )
            }
        }
    ) { paddingValues ->
        when {
            state.isLoading              -> LoadingScreen()

            state.errorMessage != null   -> {
                EmptyContentComponent(
                    painter = painterResource(Res.drawable.baseline_clear_24),
                    message = state.errorMessage.asString(),
                    withRetryButton = true,
                    onRetryClick = {
                        onAction(ProductDetailAction.OnRetryClick)
                    },
                    withGoBackButton = true,
                    onGoBackClick = {
                        onAction(ProductDetailAction.OnGoBackClick)
                    }
                )
            }

            state.currentProduct != null -> {
                ProductDetailsMovingBackground(
                    productImage = state.currentProduct.image,
                    onBackClick = {
                        onAction(ProductDetailAction.OnGoBackClick)
                    }
                ) {
                    ProductDetailsInfo(
                        state = state,
                        bottomPadding = paddingValues.calculateBottomPadding()
                    ) { product ->
                        onAction(ProductDetailAction.OnAddToFavoritesClick(product))
                    }
                }
            }
        }
    }
}