package org.example.project.features.recipesList.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.RecipeDao
import org.example.project.core.domain.model.Recipe
import org.example.project.features.recipesList.domain.api.RecipesRepository

class RecipesRepositoryImpl(
    private val recipeDao: RecipeDao,
    private val coffeeDao: CoffeeDao,
    private val dispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override suspend fun getRecentRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecentRecipes()
            .map { recipeList ->
                recipeList.map {
                    val coffee = coffeeDao.getCoffeeDetails(it.coffeeEntityId).toModel()
                    it.toModel(coffee)
                }
            }
            .flowOn(dispatcher)
    }
}