package app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.navigation.NavGraph
import app.presentation.AppAction
import app.presentation.AppState
import app.presentation.MainViewModel
import core.presentation.MyApplicationTheme
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    val mainViewModel = koinViewModel<MainViewModel>()
    AppContent(viewModel = mainViewModel, navController = navController) {
        mainViewModel.onEvent(it)
    }
}

@Composable
fun AppContent(
    viewModel: MainViewModel,
    navController: NavHostController,
    onEvent: (AppAction) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MyApplicationTheme(appTheme = state.appTheme) {
        NavGraph(navGraphNavigator = navController) { action ->
            onEvent(action)
        }
    }
}