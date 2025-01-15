package app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import home.presentation.HomeScreenRoot
import home.presentation.viewmodel.HomePageViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(navGraphNavigator: NavHostController) {
    NavHost(
        navController = navGraphNavigator,
        startDestination = Screens.Home.route,
    ) {
        composable(route = Screens.Home.route) {
            val viewModel = koinViewModel<HomePageViewModel>()
            HomeScreenRoot(
                viewModel = viewModel,
                onBackClick = {
                    navGraphNavigator.popBackStack()
                }
            )
        }
    }
}