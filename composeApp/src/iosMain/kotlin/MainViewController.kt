import androidx.compose.ui.window.ComposeUIViewController
import app.App
import di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}