package e_commerce.presentation.products_map.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import mapViewController

@Composable
actual fun MapComponent() {
    UIKitViewController(
        factory = mapViewController,
        modifier = Modifier.fillMaxSize()
    )
}