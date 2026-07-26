package org.example.project.features.newRecipe.ui.state

import org.example.project.core.domain.model.Recipe

sealed interface NewRecipeScreenUiState {
    data object WaterAmountDialog : NewRecipeScreenUiState
    data object Loading : NewRecipeScreenUiState
    data class Content(
        val imagePath: String?,
        val recipe: Recipe
    ) : NewRecipeScreenUiState
}