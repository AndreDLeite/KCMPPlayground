import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.App
import app.DS_FILE_NAME
import app.createDataStore
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