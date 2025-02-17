package di

import app.di.appModule
import e_commerce.di.ecommerceModule
import home.di.homeModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

expect val platformModule: Module

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        platformModule,
        appModule,
        homeModule,
        ecommerceModule
    )
}
