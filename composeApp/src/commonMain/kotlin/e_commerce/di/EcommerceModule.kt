package e_commerce.di

import core.data.HttpClientFactory
import e_commerce.presentation.product_home.ProductHomeViewModel
import e_commerce.presentation.splash.ECommerceSplashViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

internal val ecommerceModule: Module = module {
    getDataSources(this)
    getRepository(this)
    getUseCases(this)
    getViewModels(this)
    getFactories(this)

}

private fun getDataSources(module: Module) = with(module) {

}

private fun getRepository(module: Module) = with(module) {

}

private fun getUseCases(module: Module) = with(module) {

}

private fun getViewModels(module: Module) = with(module) {
    factory { ECommerceSplashViewModel() }
    factory { ProductHomeViewModel() }
}

private fun getFactories(module: Module) = with(module) {
    single { HttpClientFactory.create(get()) }
}