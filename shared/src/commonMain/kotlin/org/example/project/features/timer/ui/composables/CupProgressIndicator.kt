package org.example.project.features.timer.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.lightGray

@Preview
@Composable
fun CupProgressBar(
    progress: Float = 0.5f,
    modifier: Modifier = Modifier,
) {
    val animatedProgress by animateFloatAsState(
        progress.coerceIn(0f, 1f),
        animationSpec = tween(1000)
    )

    Box(
        modifier = modifier
            .size(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height

            val cupPath = Path().apply {
                moveTo(x = width * 0f, y = height * 0f)
                moveTo(x = width, y = height * 0f)
                cubicTo(
                    x1 = width, y1 = height,
                    x2 = width, y2 = height,
                    x3 = width * 0.5f, y3 = height
                )
                cubicTo(
                    x1 = width * 0f, y1 = height,
                    x2 = width * 0f, y2 = height,
                    x3 = width * 0, y3 = height * 0
                )
            }

            drawPath(
                path = cupPath,
                color = lightGray,
                style = Stroke(
                    width = 20f,
                    cap = StrokeCap.Round
                )
            )
            drawPath(
                path = cupPath,
                color = backgroundColor,
            )

            val coffeeHeight = size.height * (1f - animatedProgress)
            clipPath(
                path = cupPath
            ) {
                drawRect(
                    color = Color(0xFF6F4E37),
                    topLeft = Offset(0f, coffeeHeight),
                    size = Size(
                        width = size.width,
                        height = size.height
                    )
                )
            }
        }
    }
}