package com.ruchabhattjoshi.cstask

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ruchabhattjoshi.cstask.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(sharedModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "CSTask",
    ) {
        App()
    }
}