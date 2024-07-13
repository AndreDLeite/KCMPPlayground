package di

import domain.usacese.ExampleUseCase
import domain.usacese.ExampleUseCaseImpl
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ui.viewmodel.HomePageViewModel

//expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        provideDataSourceModule,
        provideRepositoryModule,
        provideUseCaseModule,
        provideViewModelModule,
//        platformModule(),
    )
}

val provideDataSourceModule = module {
    //singleOf(::ExampleDataSourceImpl).bind<ExampleDataSource>()
}

val provideRepositoryModule = module {
    //singleOf(::ExampleDataSourceImpl).bind<ExampleDataSource>()
}

val provideUseCaseModule = module {
    single<ExampleUseCase> { ExampleUseCaseImpl() }
}

val provideViewModelModule = module {
    factory { HomePageViewModel(get()) }
}