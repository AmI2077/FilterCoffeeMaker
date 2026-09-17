package org.example.project.core.domain.api.repository

import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.Result
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

interface AiClientRepository {

    suspend fun getCoffeeFromImage(imageBase64: String): Result<Coffee>

    suspend fun getRecipeFromAi(recipeRequest: RecipeRequest): Result<Recipe>
}