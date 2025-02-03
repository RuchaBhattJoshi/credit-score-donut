package com.ruchabhattjoshi.cstask.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.ui.components.ClearScoreDonut
import com.ruchabhattjoshi.cstask.ui.components.ClearScoreRefresh

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel = koinScreenModel<HomeViewModel>()
        val clearScoreInfo by viewModel.clearScoreInfo.collectAsState()
        var shouldRefresh by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = shouldRefresh) {
            if (shouldRefresh) {
                viewModel.fetchNewCreditInfo()
                shouldRefresh = false
            }
        }

        HomeScreenContent(
            requestState = clearScoreInfo,
            onRefreshClicked = { shouldRefresh = true }
        )
    }
}

@Composable
fun HomeScreenContent(
    requestState: RequestState<CreditInfo>?,
    onRefreshClicked: () -> Unit
) {
    when (requestState) {
        is RequestState.Error -> ErrorScreen(
            error = requestState.message,
            onRefreshClicked = onRefreshClicked
        )

        RequestState.Idle, RequestState.Loading -> LoadingScreen()

        is RequestState.Success -> {
            val creditInfo = requestState.data
            if (isValidCreditInfo(creditInfo)) {
                ClearScoreDonut(
                    maxScoreValue = creditInfo.creditReportInfo.maxScoreValue,
                    score = creditInfo.creditReportInfo.score
                )
            } else {
                ErrorScreen(
                    error = "Invalid credit information",
                    onRefreshClicked = onRefreshClicked
                )
            }
        }

        null -> ErrorScreen(
            error = "An unexpected error occurred",
            onRefreshClicked = onRefreshClicked
        )
    }
}

@Composable
fun LoadingScreen() {
    ClearScoreDonut(maxScoreValue = 100, score = 0, isSkeleton = true)
}

@Composable
fun ErrorScreen(error: String, onRefreshClicked: () -> Unit) {
    ClearScoreRefresh(error = error, onRefreshClicked = onRefreshClicked)
}

fun isValidCreditInfo(creditInfo: CreditInfo): Boolean {
    val maxScoreValue = creditInfo.creditReportInfo.maxScoreValue
    val score = creditInfo.creditReportInfo.score
    val minScoreValue = creditInfo.creditReportInfo.minScoreValue

    return !(minScoreValue < 0 || score < 0 || maxScoreValue < 0 || score < minScoreValue || score > maxScoreValue)
}

