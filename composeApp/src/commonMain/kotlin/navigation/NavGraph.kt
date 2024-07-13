package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.pages.HomePage

@Composable
fun NavGraph(navGraphNavigator: NavHostController) {
    NavHost(
        navController = navGraphNavigator,
        startDestination = Screens.Home.route,
    ) {
        composable(route = Screens.Home.route) {
            HomePage(navGraphNavigator)
        }
    }
}