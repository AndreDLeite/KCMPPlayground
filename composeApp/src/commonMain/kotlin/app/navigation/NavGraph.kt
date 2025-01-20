package app.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import e_commerce.presentation.product_home.ProductHomeScreenRoot
import e_commerce.presentation.product_home.ProductHomeViewModel
import e_commerce.presentation.splash.ECommerceSplashScreenRoot
import e_commerce.presentation.splash.ECommerceSplashViewModel
import home.presentation.ProjectsHubHomeScreenRoot
import home.presentation.viewmodel.HomePageViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(navGraphNavigator: NavHostController) {
    NavHost(
        navController = navGraphNavigator,
        startDestination = Screens.ProjectsHub,
    ) {
        navigation<Screens.ProjectsHub>(
            startDestination = Screens.ProjectsHubHome
        ) {
            composable<Screens.ProjectsHubHome> {
                val viewModel = koinViewModel<HomePageViewModel>()
                ProjectsHubHomeScreenRoot(
                    viewModel = viewModel,
                ) {
                    navGraphNavigator.navigate(Screens.ECommerceGraph)
                }
            }
        }

        navigation<Screens.ECommerceGraph>(
            startDestination = Screens.ECommerceSplash
        ) {
            composable<Screens.ECommerceSplash> {
                val viewModel = koinViewModel<ECommerceSplashViewModel>()

                ObserveAsEvents(viewModel.state) { event ->
                    if (event) {
                        navGraphNavigator.navigate(Screens.ECommerceHome) {
                            popUpTo(Screens.ECommerceSplash) { inclusive = true }
                        }
                    }
                }
                ECommerceSplashScreenRoot()
            }

            composable<Screens.ECommerceHome> {
                val viewModel = koinViewModel<ProductHomeViewModel>()

                ProductHomeScreenRoot(
                    viewModel = viewModel,
                    onFavoritesClick = {
                        navGraphNavigator.navigate(Screens.ECommerceFavorites)
                    },
                    onShoppingCartClick = {
                        navGraphNavigator.navigate(Screens.ECommerceCart)
                    },
                    onProductClick = { productId ->
                        navGraphNavigator.navigate(Screens.ECommerceProductDetails(productId))
                    }
                )
            }

            composable<Screens.ECommerceFavorites> {
                Text("Tela de favoritos!")
            }

            composable<Screens.ECommerceCart> {
                Text("Tela do carrinho!")
            }

            composable<Screens.ECommerceProductDetails> { entry ->
                val args = entry.toRoute<Screens.ECommerceProductDetails>()

                //ProductDetailsScreen(productId = args.productId, navController = navGraphNavigator)

            }
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