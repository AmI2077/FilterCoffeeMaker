package org.example.project.features.recipeDetails.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.example.project.core.data.AiConfig
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.local.db.dao.RecipeDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkResult
import org.example.project.core.data.resources.ResourceManager
import org.example.project.core.domain.model.Recipe
import org.example.project.features.recipeDetails.data.extensions.RecipeResponseSerializer
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

class RecipeDetailsRepositoryImpl(
    private val aiClient: AiClient,
    private val recipeDao: RecipeDao,
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatcher,
) : RecipeDetailsRepository {
    override suspend fun getRecipe(recipeRequest: RecipeRequest): Recipe {
        return withContext(dispatcher) {
            val coffeeJson = Json.encodeToJsonElement(recipeRequest).toString()
            val prompt = resourceManager.getFileResource("files/RecipePrompt.txt")

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

    override suspend fun saveRecipeToRecents(recipe: Recipe, coffeeId: Int) {
        withContext(dispatcher) {
            recipeDao.insertRecipe(recipe.toEntity(coffeeId))
        }
    }
}