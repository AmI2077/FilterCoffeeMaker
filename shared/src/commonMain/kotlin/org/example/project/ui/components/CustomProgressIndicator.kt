package org.example.project.ui.components

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.lightGray

@Preview
@Composable
fun CustomProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0.5f,
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = black,
        strokeCap = StrokeCap.Round,
        trackColor = lightGray,
        progress = { progress }
    )
}