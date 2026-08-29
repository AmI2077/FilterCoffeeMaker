package org.example.project.features.recipeDetails.store

import org.example.project.core.domain.model.Recipe

data class RecipeDetailsScreenUiState(
    val showWaterAmountDialog: Boolean = false,
    val isLoading: Boolean = false,
    val isFavourite: Boolean = false,
    val content: Recipe? = null
)

sealed interface RecipeDetailsScreenIntent {
    data class DefineInitState(val recipe: Recipe?) : RecipeDetailsScreenIntent
    data class LoadRecipeDetails(val waterAmount: Int, val coffeeId: String?) :
        RecipeDetailsScreenIntent

    data class SaveRecipeDetailsToRecents(val recipe: Recipe, val coffeeId: String?) :
        RecipeDetailsScreenIntent
}

sealed interface RecipeDetailsResult {
    data object ShowWaterAmountDialog : RecipeDetailsResult
    data object Loading : RecipeDetailsResult
    data class RecipeDetailsLoaded(val recipe: Recipe) : RecipeDetailsResult
}

sealed interface RecipeDetailsAction {
    data class OpenTimerScreen(val recipe: Recipe): RecipeDetailsAction
}
