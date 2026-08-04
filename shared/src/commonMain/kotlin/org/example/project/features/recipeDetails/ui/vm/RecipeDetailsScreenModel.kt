package org.example.project.features.recipeDetails.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Recipe
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipeDetails.domain.models.RecipeRequest
import org.example.project.features.recipeDetails.ui.state.RecipeDetailsScreenIntent
import org.example.project.features.recipeDetails.ui.state.RecipeDetailsScreenUiState
import kotlin.time.Duration.Companion.seconds

class RecipeDetailsScreenModel(
    private val coffeeId: Int,
    private val imageSaver: ImageSaver,
    private val recipeDetailsRepository: RecipeDetailsRepository,
    private val coffeeDetailsRepository: CoffeeDetailsRepository
) : ScreenModel {

    private var _state =
        MutableStateFlow<RecipeDetailsScreenUiState>(RecipeDetailsScreenUiState.WaterAmountDialog)
    val state = _state.asStateFlow()


    fun onIntent(intent: RecipeDetailsScreenIntent) {
        when (intent) {
            is RecipeDetailsScreenIntent.LoadRecipeDetails -> {
                getRecipe(waterAmount = intent.waterAmount)
            }

            is RecipeDetailsScreenIntent.SaveRecipeDetailsToRecents -> {
                saveRecipeToRecents(intent.recipe, coffeeId)
            }
        }
    }

    private fun saveRecipeToRecents(recipe: Recipe, coffeeId: Int) {
        screenModelScope.launch {
            recipeDetailsRepository.saveRecipeToRecents(recipe, coffeeId)
        }
    }

    private fun getRecipe(waterAmount: Int) {
        _state.update {
            RecipeDetailsScreenUiState.Loading
        }
        screenModelScope.launch {
            delay(10.seconds)
            val coffee = coffeeDetailsRepository.getCoffeeDetails(coffeeId)

            val directory = coffee.imagePath?.let {
                imageSaver.getDirectory(it)
            }

            val recipeRequest = RecipeRequest(
                coffee = coffee,
                waterAmount = waterAmount,
            )
            val recipe = recipeDetailsRepository.getRecipe(recipeRequest)

            recipeDetailsRepository.saveRecipeToRecents(recipe, coffeeId)
            _state.update {
                RecipeDetailsScreenUiState.Content(
                    imagePath = directory,
                    recipe = recipe
                )
            }
        }
    }
}