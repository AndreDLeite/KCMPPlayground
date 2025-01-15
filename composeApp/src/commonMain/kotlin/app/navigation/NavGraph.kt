package app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import e_commerce.presentation.product_home.ProductHomeScreenRoot
import e_commerce.presentation.product_home.ProductHomeViewModel
import e_commerce.presentation.splash.ECommerceSplashScreenRoot
import e_commerce.presentation.splash.ECommerceSplashViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(navGraphNavigator: NavHostController) {
    NavHost(
        navController = navGraphNavigator,
        startDestination = Screens.ECommerceSplash.route,
    ) {
        composable(route = Screens.Home.route) {
            //TODO: Fazer uma lista de selecao de features aqui talvez
        }
        composable(route = Screens.ECommerceSplash.route) {
            val viewModel = koinViewModel<ECommerceSplashViewModel>()

            ObserveAsEvents(viewModel.state) { event ->
                if(event) {
                    navGraphNavigator.navigate(Screens.ECommerceHome.route)
                }
            }
            ECommerceSplashScreenRoot()
        }

        composable(route = Screens.ECommerceHome.route) {
            val viewModel = koinViewModel<ProductHomeViewModel>()

            ProductHomeScreenRoot(
                viewModel,
                onFavoritesClick = {
                    navGraphNavigator.navigate(Screens.ECommerceFavorites.route)
                },
                onShoppingCartClick = {
                    navGraphNavigator.navigate(Screens.ECommerceCart.route)
                }
            )
        }
    }
}

@Composable
private fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }
}