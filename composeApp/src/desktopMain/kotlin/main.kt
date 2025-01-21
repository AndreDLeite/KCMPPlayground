import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.App
import di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KMPPlayground",
        ) {
            App()
        }
    }
}