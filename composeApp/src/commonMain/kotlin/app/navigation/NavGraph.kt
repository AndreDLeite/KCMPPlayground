package app.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import app.presentation.AppAction
import core.presentation.AppTheme
import e_commerce.presentation.favorite_products.FavoriteProductsScreenRoot
import e_commerce.presentation.favorite_products.FavoriteProductsViewModel
import e_commerce.presentation.product_detail.ProductDetailsScreenRoot
import e_commerce.presentation.product_detail.ProductDetailsViewModel
import e_commerce.presentation.product_home.ProductHomeScreenRoot
import e_commerce.presentation.product_home.ProductHomeViewModel
import e_commerce.presentation.splash.ECommerceSplashScreenRoot
import e_commerce.presentation.splash.ECommerceSplashViewModel
import home.presentation.ProjectsHubHomeScreenRoot
import home.presentation.HomePageViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph(
    navGraphNavigator: NavHostController,
    onGlobalEvent: (AppAction) -> Unit = {}
) {
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
                    },
                    onSettingsClick = {
                        onGlobalEvent(AppAction.OnThemeChange(AppTheme.MacRed))
                    },
                    onProfileClick = {
                        onGlobalEvent(AppAction.OnThemeChange(AppTheme.GoldenYellow))
                    },
                    onNotificationsClick = {
                        onGlobalEvent(AppAction.OnThemeChange(AppTheme.BlueySky))
                    }
                )
            }

            composable<Screens.ECommerceFavorites> {
                val viewModel = koinViewModel<FavoriteProductsViewModel>()
                FavoriteProductsScreenRoot(
                    viewModel = viewModel,
                    onGoBackClick = {
                        navGraphNavigator.navigateUp()
                    },
                    onProductClick = { productId ->
                        navGraphNavigator.navigate(Screens.ECommerceProductDetails(productId))
                    }
                )
            }

            composable<Screens.ECommerceCart> {
                Text("WIP")
            }

            composable<Screens.ECommerceProductDetails> { entry ->
                val args = entry.toRoute<Screens.ECommerceProductDetails>()
                val viewModel = koinViewModel<ProductDetailsViewModel> {
                    parametersOf(args.productId)
                }

                ProductDetailsScreenRoot(
                    viewModel = viewModel
                ) {
                    navGraphNavigator.navigateUp()
                }

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