package org.example.project.features.recipeDetails.ui.state

import org.example.project.core.domain.model.Recipe

sealed interface RecipeDetailsScreenIntent {
    data class LoadRecipeDetails(val waterAmount: Int) : RecipeDetailsScreenIntent
    data class SaveRecipeDetailsToRecents(val recipe: Recipe) : RecipeDetailsScreenIntent
}