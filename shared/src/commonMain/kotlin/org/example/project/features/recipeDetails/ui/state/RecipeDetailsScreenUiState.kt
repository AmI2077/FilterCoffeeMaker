package org.example.project.features.recipeDetails.ui.state

import org.example.project.core.domain.model.Recipe

sealed interface RecipeDetailsScreenUiState {
    data object WaterAmountDialog : RecipeDetailsScreenUiState
    data object Loading : RecipeDetailsScreenUiState
    data class Content(
        val imagePath: String?,
        val recipe: Recipe
    ) : RecipeDetailsScreenUiState
}