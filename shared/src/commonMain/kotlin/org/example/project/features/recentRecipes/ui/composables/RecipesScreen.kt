package org.example.project.features.recentRecipes.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.greeting
import coffee.shared.generated.resources.ic_ai_24
import coffee.shared.generated.resources.new_recipe
import coffee.shared.generated.resources.recent_recipes_title
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.HeaderAppText
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.white
import org.example.project.features.recentRecipes.ui.vm.RecipesScreenModel
import org.example.project.features.recentRecipes.ui.vm.RecipesScreenUiState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    screenModel: RecipesScreenModel,
    onRecipeClick: (recipe: Recipe) -> Unit
) {
    val state by screenModel.state.collectAsStateWithLifecycle()

    RecipesScreenContent(
        modifier = modifier,
        state = state,
        onRecipeClick = {
            onRecipeClick(it)
        }
    )
}

@Composable
private fun RecipesScreenContent(
    modifier: Modifier = Modifier,
    state: RecipesScreenUiState,
    onRecipeClick: (recipe: Recipe) -> Unit,
) {
    Column(modifier = modifier) {
        RecipeHeader()
        Spacer(Modifier.padding(top = 30.dp))
        RegularAppText(
            modifier = Modifier.align(Alignment.End),
            text = stringResource(Res.string.recent_recipes_title),
            fontSize = 24.sp
        )
        Spacer(Modifier.padding(top = 10.dp))
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(vertical = 10.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(state.recentRecipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onRecipeClick = {
                        onRecipeClick(it)
                    }
                )
            }
        }
    }

}

@Composable
private fun RecipeHeader() {
    HeaderAppText(
        text = stringResource(Res.string.greeting)
    )
    Spacer(Modifier.padding(top = 10.dp))
    AppButton(
        modifier = Modifier.fillMaxWidth(),
        text = {
            RegularAppText(
                modifier = Modifier.padding(vertical = 15.dp),
                text = stringResource(Res.string.new_recipe),
                color = white,
            )
        },
        icon = {
            Icon(
                painter = painterResource(Res.drawable.ic_ai_24),
                contentDescription = null
            )
        },
        onClick = {}
    )
}