package org.example.project.core.ui.navigation.voyager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.theme.backgroundPrimary
import org.example.project.features.recipeDetails.ui.composables.RecipeDetailsScreen
import org.example.project.features.recipeDetails.ui.vm.RecipeDetailsScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel
import org.koin.core.parameter.parametersOf

class NewRecipeScreen(
    private val coffeeId: Int?,
    private val recipe: Recipe? = null
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Scaffold { paddingValues ->
            RecipeDetailsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundPrimary)
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
                screenModel = koinScreenModel<RecipeDetailsScreenModel>(
                    parameters = { parametersOf(coffeeId, recipe) }
                ),
                loaderScreenModel = koinScreenModel<RecipeLoaderScreenModel>(),
                onStartTimerClick = { recipe ->
                    navigator?.push(TimerScreen(recipe))
                }
            )
        }
    }
}
