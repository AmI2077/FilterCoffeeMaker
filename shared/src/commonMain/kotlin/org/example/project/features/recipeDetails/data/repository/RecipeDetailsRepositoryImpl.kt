package org.example.project.features.recipeDetails.data.repository

import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.example.project.core.data.AiConfig
import org.example.project.core.data.extensions.toRecentEntity
import org.example.project.core.data.local.db.dao.FavouritesRecipesDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkResult
import org.example.project.core.data.resources.Directories
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.api.ResourceManager
import org.example.project.core.domain.model.Recipe
import org.example.project.features.recipeDetails.data.extensions.RecipeResponseSerializer
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipeDetails.domain.models.RecipeRequest
import kotlin.random.Random

class RecipeDetailsRepositoryImpl(
    private val aiClient: AiClient,
    private val recentRecipesDao: RecentRecipesDao,
    private val favouritesRecipesDao: FavouritesRecipesDao,
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatchers,
) : RecipeDetailsRepository {
    override suspend fun getRecipe(recipeRequest: RecipeRequest): Recipe {
        return withContext(dispatcher.io()) {
            val coffeeJson = Json.encodeToJsonElement(recipeRequest).toString()
            val prompt = resourceManager.getFileResource(Directories.AI_RECIPE_PROMPT_FILE_PATH)

            val result = aiClient.makeRequest(
                AiRequestDto.makeRequest(
                    instructions = prompt,
                    text = coffeeJson,
                    imageBase64 = null,
                    model = AiConfig.getGptProModelId()
                )
            )
            when (result) {
                is NetworkResult.Error -> {
                    throw IllegalStateException(result.error.message)
                }

                is NetworkResult.Success -> {
                    val rawJson = result.data
                    val recipe = Json.decodeFromString(
                        deserializer = RecipeResponseSerializer(),
                        string = rawJson
                    )
                    recipe
                }
            }
        }
    }

    override suspend fun saveRecipeToRecents(recipe: Recipe, coffeeId: String) {
        // TODO "че то придумать с id рецепта (дублировать id кофе)"

        val recipeWithId = recipe.copy(
            id = Random.nextInt(0, 14315)
        )
        withContext(dispatcher.io()) {
            recentRecipesDao.insertRecipe(recipeWithId.toRecentEntity(coffeeId))
        }
    }

    override suspend fun saveRecipesToFavourites(recipe: Recipe) {
        withContext(dispatcher.io()) {
            //favouritesRecipesDao.insertRecipe(recipe.toFavEntity())
        }
    }
}