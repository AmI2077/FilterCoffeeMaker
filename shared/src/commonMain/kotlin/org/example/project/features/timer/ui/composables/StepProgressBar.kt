package org.example.project.features.timer.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.blueGrayText


@Composable
fun StepProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier
            .height(100.dp)
    ) {
        if (animatedProgress == 0.0f) {
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .weight(1f)
                    .background(
                        color = blueGrayText,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        } else {
            if (animatedProgress < 1) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .weight(1f - animatedProgress)
                        .background(
                            color = blueGrayText,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .weight(animatedProgress)
                    .background(
                        color = black,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
    }
}