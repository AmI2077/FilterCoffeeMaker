package org.example.project.features.recipeDetails.domain.api

import org.example.project.core.domain.model.Recipe
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

interface RecipeDetailsRepository {

    suspend fun getRecipe(recipeRequest: RecipeRequest): Recipe

    suspend fun saveRecipeToRecents(recipe: Recipe, coffeeId: String)
}