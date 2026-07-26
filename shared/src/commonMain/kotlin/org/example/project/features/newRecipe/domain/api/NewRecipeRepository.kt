package org.example.project.features.newRecipe.domain.api

import org.example.project.core.domain.model.Recipe
import org.example.project.features.newRecipe.domain.models.RecipeRequest

interface NewRecipeRepository {

    suspend fun getRecipe(recipeRequest: RecipeRequest): Recipe

    suspend fun saveRecipeToRecents(recipe: Recipe, coffeeId: Int)
}