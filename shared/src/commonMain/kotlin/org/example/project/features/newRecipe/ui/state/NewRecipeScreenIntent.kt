package org.example.project.features.newRecipe.ui.state

import org.example.project.core.domain.model.Recipe

sealed interface NewRecipeScreenIntent {
    data class LoadRecipe(val waterAmount: Int) : NewRecipeScreenIntent
    data class SaveRecipeToRecents(val recipe: Recipe) : NewRecipeScreenIntent
}