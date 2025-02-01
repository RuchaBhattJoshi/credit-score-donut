package com.ruchabhattjoshi.cstask.di


import com.ruchabhattjoshi.cstask.data.remote.api.ClearScoreApiServiceImpl
import com.ruchabhattjoshi.cstask.data.remote.api.HttpClientFactory
import com.ruchabhattjoshi.cstask.data.remote.api.ClearScoreApiService
import com.ruchabhattjoshi.cstask.presentation.screen.HomeViewModel
import com.ruchabhattjoshi.cstask.repository.CreditInfoRepository
import com.ruchabhattjoshi.cstask.repository.CreditInfoRepositoryImpl
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sharedModule = module {
    single { Settings() }
    single { HttpClientFactory.create() }
    single<ClearScoreApiService> { ClearScoreApiServiceImpl(get()) }
    single<CreditInfoRepository> { CreditInfoRepositoryImpl(get()) }
    factoryOf(::HomeViewModel)
}

fun initializeKoin() {
    startKoin {
        modules(sharedModule)
    }
}