package org.example.project.features.recipesList.domain.api

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Recipe

interface RecipesRepository {

    suspend fun getRecentRecipes(): Flow<List<Recipe>>
}