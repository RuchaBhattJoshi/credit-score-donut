package com.ruchabhattjoshi.cstask.presentation.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.ui.theme.*
import com.ruchabhattjoshi.cstask.utils.Constants
import org.jetbrains.compose.resources.InternalResourceApi
import kotlin.ranges.contains

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

@Composable
fun ClearScoreRefresh(error: String, onRefreshClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start
            )
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = onRefreshClicked,
            ) {
                Text(
                    text = "Refresh",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun ClearScoreDonut(maxScoreValue: Int, score: Int, isSkeleton: Boolean = false) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedFloat by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val shimmerColors = listOf(
        gray.copy(alpha = 0.6f),
        gray.copy(alpha = 0.2f),
        gray.copy(alpha = 0.6f),
    )

    val shimmerBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(0f, 0f),
        end = Offset(animatedFloat * 1000, animatedFloat * 1000)
    )

    val gradientYellowBrush = Brush.linearGradient(listOf(yellow600, yellow700, yellow800))
    val gradientGreenBrush = Brush.linearGradient(listOf(green600, green700, green800))
    val gradientRedBrush = Brush.linearGradient(listOf(red600, red700, red800))

    val gradientBrush: Brush

    var allowedIndicatorValue by remember {
        mutableStateOf(maxScoreValue)
    }

    allowedIndicatorValue = if (score <= maxScoreValue) {
        score
    } else {
        maxScoreValue
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue / maxScoreValue) * 100
    gradientBrush = when {
        percentage <= 20 -> {
            gradientRedBrush
        }

        percentage in 20.0..80.0 -> {
            gradientYellowBrush
        }

        else -> {
            gradientGreenBrush
        }
    }

    val sweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .wrapContentSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            inset(
                size.width / 2 - Constants.ARC_RADIUS,
                size.height / 2 - Constants.ARC_RADIUS
            ) {

                drawArc(
                    brush = if (isSkeleton) shimmerBrush else gradientBrush,
                    startAngle = -90f,
                    sweepAngle = if (isSkeleton) 360f else sweepAngle,
                    useCenter = false,
                    style = Stroke(width = Constants.ARC_WIDTH, cap = StrokeCap.Round),
                    blendMode = BlendMode.SrcIn,
                )

                drawCircle(
                    color = black,
                    radius = Constants.CIRCLE_RADIUS,
                    style = Stroke(
                        width = Constants.CIRCLE_WIDTH,
                        cap = StrokeCap.Round
                    ),
                    center = center
                )
            }
        }
        CircleText(
            maxScoreValue = maxScoreValue,
            scoreValue = receivedValue,
            isSkeleton = isSkeleton,
            brush = shimmerBrush
        )
    }

}


@OptIn(InternalResourceApi::class)
@Composable
fun CircleText(
    maxScoreValue: Int,
    scoreValue: Int = 0,
    isSkeleton: Boolean = false,
    brush: Brush = Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Credit Score is",
                color = if (isSkeleton) Color.Transparent else black,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start
            )

            Text(
                text = if (isSkeleton) "---" else scoreValue.toString(),
                style = MaterialTheme.typography.displayLarge.copy(
                    brush = if (isSkeleton) brush else Brush.linearGradient(
                        listOf(
                            yellow600,
                            yellow600
                        )
                    )
                ),
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.Center
            )

            Text(
                text = if (isSkeleton) "out of ---" else "out of $maxScoreValue",
                color = if (isSkeleton) Color.Transparent else black,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.End
            )
        }

    }
}