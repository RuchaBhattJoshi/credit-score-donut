package com.ruchabhattjoshi.cstask.ui.components

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import com.ruchabhattjoshi.cstask.ui.theme.black
import com.ruchabhattjoshi.cstask.ui.theme.gray
import com.ruchabhattjoshi.cstask.ui.theme.green600
import com.ruchabhattjoshi.cstask.ui.theme.green700
import com.ruchabhattjoshi.cstask.ui.theme.green800
import com.ruchabhattjoshi.cstask.ui.theme.red600
import com.ruchabhattjoshi.cstask.ui.theme.red700
import com.ruchabhattjoshi.cstask.ui.theme.red800
import com.ruchabhattjoshi.cstask.ui.theme.yellow600
import com.ruchabhattjoshi.cstask.ui.theme.yellow700
import com.ruchabhattjoshi.cstask.ui.theme.yellow800
import com.ruchabhattjoshi.cstask.utils.Constants

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

