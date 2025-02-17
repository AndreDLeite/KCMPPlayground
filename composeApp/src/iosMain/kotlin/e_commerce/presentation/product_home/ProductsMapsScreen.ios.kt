package e_commerce.presentation.product_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import androidx.compose.ui.viewinterop.UIKitViewController
import mapViewController

@Composable
actual fun MapComponent(
    state: ProductHomeState,
    bottomPadding: Dp,
    onProductClick: (String) -> Unit
) {
    Box {
//        UIKitView(
//            factory = {
//            }
//        )
        UIKitViewController(
            factory = mapViewController,
            modifier = Modifier.fillMaxSize()
        )
    }
}