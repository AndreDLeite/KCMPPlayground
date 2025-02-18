import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import app.App
import app.createDataStore
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
    App(
        prefs = remember { createDataStore() }
    )
}

lateinit var mapViewController: () -> UIViewController
 