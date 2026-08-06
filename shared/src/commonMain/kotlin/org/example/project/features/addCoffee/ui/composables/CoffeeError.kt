package org.example.project.features.addCoffee.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.core.ui.components.RegularAppText

@Composable
fun CoffeeError(
    modifier: Modifier = Modifier,
    message: String,
) {
    RegularAppText(
        modifier = modifier,
        text = message,
        maxLines = 2
    )
}