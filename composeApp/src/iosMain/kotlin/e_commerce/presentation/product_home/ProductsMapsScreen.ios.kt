package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import mapViewController

@Composable
actual fun MapComponent(
    state: ProductHomeState,
    onProductClick: (String) -> Unit
) {
    Box {
        UIKitViewController(
            factory = mapViewController,
            modifier = Modifier.fillMaxSize()
        )
    }
}