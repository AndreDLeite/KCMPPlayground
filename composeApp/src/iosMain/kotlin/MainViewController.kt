import androidx.compose.ui.window.ComposeUIViewController
import app.App
import di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(
    mapUIViewController: () -> UIViewController
) = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    mapViewController = mapUIViewController
    App()
}

lateinit var mapViewController: () -> UIViewController
 