package com.ruchabhattjoshi.cstask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ruchabhattjoshi.cstask.ui.theme.black
import com.ruchabhattjoshi.cstask.ui.theme.yellow600

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