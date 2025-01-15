package di

import e_commerce.di.ecommerceModule
import home.domain.usacese.ExampleUseCase
import home.domain.usacese.ExampleUseCaseImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import home.presentation.viewmodel.HomePageViewModel
import org.koin.core.module.Module

expect val platformModule: Module

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        platformModule,
        ecommerceModule
    )
}
