package org.example.project.features.newRecipe.ui.vm

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
import org.example.project.features.newRecipe.domain.api.NewRecipeRepository
import org.example.project.features.newRecipe.domain.models.RecipeRequest
import org.example.project.features.newRecipe.ui.state.NewRecipeScreenIntent
import org.example.project.features.newRecipe.ui.state.NewRecipeScreenUiState
import kotlin.time.Duration.Companion.seconds

class NewRecipeScreenModel(
    private val coffeeId: Int,
    private val imageSaver: ImageSaver,
    private val newRecipeRepository: NewRecipeRepository,
    private val coffeeDetailsRepository: CoffeeDetailsRepository
) : ScreenModel {

    private var _state =
        MutableStateFlow<NewRecipeScreenUiState>(NewRecipeScreenUiState.WaterAmountDialog)
    val state = _state.asStateFlow()


    fun onIntent(intent: NewRecipeScreenIntent) {
        when (intent) {
            is NewRecipeScreenIntent.LoadRecipe -> {
                getRecipe(waterAmount = intent.waterAmount)
            }

            is NewRecipeScreenIntent.SaveRecipeToRecents -> {
                saveRecipeToRecents(intent.recipe, coffeeId)
            }
        }
    }

    private fun saveRecipeToRecents(recipe: Recipe, coffeeId: Int) {
        screenModelScope.launch {
            newRecipeRepository.saveRecipeToRecents(recipe, coffeeId)
        }
    }

    private fun getRecipe(waterAmount: Int) {
        _state.update {
            NewRecipeScreenUiState.Loading
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
            val recipe = newRecipeRepository.getRecipe(recipeRequest)

            newRecipeRepository.saveRecipeToRecents(recipe, coffeeId)
            _state.update {
                NewRecipeScreenUiState.Content(
                    imagePath = directory,
                    recipe = recipe
                )
            }
        }
    }
}