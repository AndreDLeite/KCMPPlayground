package app.di

import app.data.datasource.LocalDataSource
import app.data.datasource.LocalDataSourceImpl
import app.data.repository.LocalDataRepositoryImpl
import app.domain.repository.LocalDataRepository
import app.presentation.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal val appModule: Module = module {
    getDataSources(this)
    getRepository(this)
    getViewModels(this)

}

private fun getDataSources(module: Module) = with(module) {
    factory<LocalDataSource> { LocalDataSourceImpl(get()) }
}

private fun getRepository(module: Module) = with(module) {
    factory<LocalDataRepository> { LocalDataRepositoryImpl(get()) }
}

private fun getViewModels(module: Module) = with(module) {
    factory { MainViewModel(get()) }
}
