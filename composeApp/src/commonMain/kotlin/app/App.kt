package app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.navigation.NavGraph
import app.presentation.AppAction
import app.presentation.MainViewModel
import core.presentation.theme.MyApplicationTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

// Presentation -> Domain <- Data
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