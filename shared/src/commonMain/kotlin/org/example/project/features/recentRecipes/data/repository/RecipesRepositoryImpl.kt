package org.example.project.features.recentRecipes.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.domain.model.Recipe
import org.example.project.features.recentRecipes.domain.api.RecipesRepository

class RecipesRepositoryImpl(
    private val recentRecipesDao: RecentRecipesDao,
    private val coffeeDao: CoffeeDao,
    private val dispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override suspend fun getRecentRecipes(): Flow<List<Recipe>> {
        return recentRecipesDao.getRecentRecipes()
            .map { recipeList ->
                recipeList.map {
                    val coffee = coffeeDao.getCoffeeDetails(it.coffeeEntityId)?.toModel()
                    it.toModel(coffee!!)
                }
            }
            .flowOn(dispatcher)
    }
}