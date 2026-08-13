package org.example.project.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.headerTextStyle

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Мои рецепты",
            style = headerTextStyle
        )
    }
}

@Preview
@Composable
fun SavedRecipesPreview() {
    SavedRecipesScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 20.dp)
    )
}