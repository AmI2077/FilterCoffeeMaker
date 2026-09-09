package org.example.project.core.data.impl.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.core.data.extensions.toFavEntity
import org.example.project.core.data.extensions.toModel
import org.example.project.core.data.extensions.toRecentEntity
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.FavouritesRecipesDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.domain.api.repository.RecipesRepository
import org.example.project.core.domain.model.Recipe

class RecipesRepositoryImpl(
    private val recentRecipesDao: RecentRecipesDao,
    private val favouritesRecipesDao: FavouritesRecipesDao,
    private val coffeeDao: CoffeeDao,
): RecipesRepository {
    override fun getRecentRecipes(): Flow<List<Recipe>> {
        return recentRecipesDao.getRecentRecipes()
            .map { list ->
                list.map { recipe ->
                    // TODO "неправильно так делать, надо че то придумать с этим
                    //  (подгружать инфу про кофе отдельно, просто передавая id из рецепта,
                    //  а не делать это во время маппинга потока)"

                    val coffee =
                        coffeeDao.getCoffeeDetails(recipe.coffeeEntityId)?.toModel()

                    recipe.toModel(coffee!!)
                }
            }
    }

    override fun getFavouritesRecipes(): Flow<List<Recipe>> {
        return favouritesRecipesDao.getFavouritesRecipes()
            .map { list ->
                list.map { recipe ->
                    // TODO "та же хуйня"

                    val coffee =
                        coffeeDao.getCoffeeDetails(recipe.coffeeEntityId)?.toModel()

                    recipe.toModel(coffee!!)
                }
            }
    }

    // TODO "не нравится мне что передается coffeeId, надо будет придумать че то с этим"
    override suspend fun addRecipeToRecents(recipe: Recipe, coffeeId: String): Boolean {
        return recentRecipesDao.insertRecipe(recipe.toRecentEntity(coffeeId)) > 0
    }

    override suspend fun addRecipeToFavourites(recipe: Recipe, coffeeId: String): Boolean {
        return favouritesRecipesDao.insertRecipe(recipe.toFavEntity(coffeeId)) > 0
    }
}