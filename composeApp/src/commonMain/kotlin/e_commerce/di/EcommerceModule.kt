package e_commerce.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import core.data.HttpClientFactory
import e_commerce.data.database.DatabaseFactory
import e_commerce.data.database.FavoriteProductDatabase
import e_commerce.data.network.KtorRemoteProductDataSource
import e_commerce.data.network.RemoteProductDataSource
import e_commerce.data.repository.ProductRepositoryImpl
import e_commerce.domain.repository.ProductRepository
import e_commerce.presentation.product_detail.ProductDetailsViewModel
import e_commerce.presentation.product_home.ProductHomeViewModel
import e_commerce.presentation.splash.ECommerceSplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val ecommerceModule: Module = module {
    getDataSources(this)
    getRepository(this)
    getViewModels(this)
    getFactories(this)

}

private fun getDataSources(module: Module) = with(module) {
    factory<RemoteProductDataSource> { KtorRemoteProductDataSource(get()) }
}

private fun getRepository(module: Module) = with(module) {
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
}

private fun getViewModels(module: Module) = with(module) {
    viewModel { ECommerceSplashViewModel() }
    viewModel { ProductHomeViewModel(get()) }
    viewModel { (productId: String) -> ProductDetailsViewModel(productId, get()) }
}

private fun getFactories(module: Module) = with(module) {
    single { HttpClientFactory.create(get()) }
    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()

    }
    single { get<FavoriteProductDatabase>().favoritesProductDao }
}