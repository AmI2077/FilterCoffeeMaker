package org.example.project.core.domain.api.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Recipe

interface RecipesRepository {

    fun getRecentRecipes(): Flow<List<Recipe>>

    fun getFavouritesRecipes(): Flow<List<Recipe>>

    suspend fun addRecipeToRecents(recipe: Recipe, coffeeId: String): Boolean

    suspend fun addRecipeToFavourites(recipe: Recipe, coffeeId: String): Boolean
}