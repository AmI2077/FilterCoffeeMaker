package org.example.project.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun AppTheme(
    content: @Composable (() -> Unit),
) {
    val density = LocalDensity.current

    val customDensity = Density(
        density = density.density,
        fontScale = 1.0f,
    )

    CompositionLocalProvider(LocalDensity provides customDensity) {
        MaterialTheme {
            content.invoke()
        }
    }
}