package com.ruchabhattjoshi.cstask.di


import com.ruchabhattjoshi.cstask.data.remote.api.ClearScoreApiServiceImpl
import com.ruchabhattjoshi.cstask.data.remote.api.HttpClientFactory
import com.ruchabhattjoshi.cstask.domain.ClearScoreApiService
import com.ruchabhattjoshi.cstask.presentation.screen.HomeViewModel
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.dsl.module

val sharedModule = module {
    single { Settings() }
    single { HttpClientFactory.create() }
    single<ClearScoreApiService> { ClearScoreApiServiceImpl(get()) }
    factory {
        HomeViewModel(
            api = get()
        )
    }
}

fun initializeKoin() {
    startKoin {
        modules(sharedModule)
    }
}