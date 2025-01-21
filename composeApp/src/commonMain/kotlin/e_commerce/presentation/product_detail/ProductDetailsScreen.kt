package e_commerce.presentation.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.DSDefaultText
import core.presentation.EmptyContentComponent
import core.presentation.LightOrange
import core.presentation.LoadingScreen
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
            when(action) {
                is ProductDetailAction.OnGoBackClick -> onGoBackClick()
                else -> viewModel.onAction(action)
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
            .fillMaxSize(),
        bottomBar = {
            state.currentProduct?.let {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = LightOrange),
                        onClick = {

                        }
                    ) {
                        Text("Add to cart", color = Color.Black)
                    }
                }
            }
        }
    ) { _ ->
        when  {
            state.isLoading -> LoadingScreen()

            state.errorMessage != null -> {
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
                    DSDefaultText("AOooowba olha eu aqui!")
                }
            }
        }
    }
}