package org.example.project.features.savedRecipes.ui.composables

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
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.my_recipes_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.my_recipes_title),
            style = headerTextStyle
        )
    }
}

@Composable
fun SavedRecipesContent(
    modifier: Modifier = Modifier,
) {

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