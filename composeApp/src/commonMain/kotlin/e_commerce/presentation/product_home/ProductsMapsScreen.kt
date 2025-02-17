package e_commerce.presentation.product_home

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
expect fun MapComponent(
    state: ProductHomeState,
    bottomPadding: Dp = 0.dp,
    onProductClick: (String) -> Unit,
)