package org.example.project.features.recentRecipes.ui.vm

import org.example.project.core.domain.model.Recipe

data class RecipesScreenUiState(
    val recentRecipes: List<Recipe> = emptyList()
)
