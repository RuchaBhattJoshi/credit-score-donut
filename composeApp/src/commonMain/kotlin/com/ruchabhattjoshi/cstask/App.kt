package com.ruchabhattjoshi.cstask


import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.ruchabhattjoshi.cstask.presentation.screen.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    Navigator(HomeScreen())
}

