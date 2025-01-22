package home.di

import home.presentation.HomePageViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal val homeModule: Module = module {
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
    factory { HomePageViewModel() }
}

private fun getFactories(module: Module) = with(module) {

}