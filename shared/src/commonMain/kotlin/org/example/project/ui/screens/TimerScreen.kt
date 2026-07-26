package org.example.project.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.ui.theme.backgroundPrimary
import org.example.project.core.ui.theme.headerTextStyle

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Timer",
            style = headerTextStyle
        )
    }
}

@Preview
@Composable
fun TimerScreenPreview() {
    TimerScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundPrimary)
    )
}