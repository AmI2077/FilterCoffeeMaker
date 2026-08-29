package org.example.project.features.recipeDetails.store

import org.example.project.core.ui.store.MviReducer

class RecipeDetailsReducer : MviReducer<RecipeDetailsScreenUiState, RecipeDetailsResult> {
    override fun reduce(
        oldState: RecipeDetailsScreenUiState,
        result: RecipeDetailsResult
    ): RecipeDetailsScreenUiState {
        return when(result) {
            is RecipeDetailsResult.RecipeDetailsLoaded -> {
                oldState.copy(
                    isLoading = false,
                    content = result.recipe
                )
            }

            RecipeDetailsResult.ShowWaterAmountDialog -> {
                oldState.copy(
                    showWaterAmountDialog = true
                )
            }

            RecipeDetailsResult.Loading -> {
                oldState.copy(
                    showWaterAmountDialog = false,
                    isLoading = true,
                )
            }
        }
    }
}