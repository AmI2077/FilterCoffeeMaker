package org.example.project.core.data.impl.repository

import io.ktor.client.statement.HttpResponse
import org.example.project.core.data.AiConfig
import org.example.project.core.data.extensions.toStringJson
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.resources.Directories
import org.example.project.core.domain.api.NetworkResponseHandler
import org.example.project.core.domain.api.ResourceManager
import org.example.project.core.domain.api.json.JsonSerializerFactory
import org.example.project.core.domain.api.repository.AiClientRepository
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.NetworkResult
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.Result
import org.example.project.core.data.impl.json.kSerializers.CoffeeResponseSerializer
import org.example.project.core.data.impl.json.kSerializers.RecipeResponseSerializer
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

// TODO "времянка"
private const val COFFEE_REQUEST_TEXT = "Опиши пачку на изображении"

private inline fun <T> NetworkResult<String>.toResult(
    serialize: (String) -> T
): Result<T> {
    return when (this) {
        is NetworkResult.Error -> {
            Result.Error(this.error.message)
        }

        is NetworkResult.Success -> {
            Result.Content(
                serialize(this.data)
            )
        }
    }
}

class AiClientRepositoryImpl(
    private val aiClient: AiClient,
    private val resourceManager: ResourceManager,
    private val responseHandler: NetworkResponseHandler<HttpResponse>,
    serializerFactory: JsonSerializerFactory,
) : AiClientRepository {
    private val coffeeSerializer = serializerFactory.create(
        clazz = Coffee::class,
        kSerializer = CoffeeResponseSerializer()
    )
    private val recipeSerializer = serializerFactory.create(
        clazz = Recipe::class,
        kSerializer = RecipeResponseSerializer()
    )

    override suspend fun getCoffeeFromImage(imageBase64: String): Result<Coffee> {
        val request = getCoffeeRequest(imageBase64)

        val result = responseHandler.handle(aiClient.makeRequest(request), String::class)

        return result.toResult { coffeeSerializer.deserialize(it) }
    }

    override suspend fun getRecipeFromAi(recipeRequest: RecipeRequest): Result<Recipe> {
        val coffeeJson = recipeRequest.toStringJson()
        val request = getRecipeRequest(coffeeJson)

        val result = responseHandler.handle(aiClient.makeRequest(request), String::class)

        return result.toResult { recipeSerializer.deserialize(it) }
    }

    private suspend fun getCoffeeRequest(imageBase64: String): AiRequestDto {
        val prompt = resourceManager.getFileResource(Directories.AI_COFFEE_PROMPT_FILE_PATH)

        return AiRequestDto.makeRequest(
            instructions = prompt,
            text = COFFEE_REQUEST_TEXT,
            imageBase64 = imageBase64,
            model = AiConfig.getQwenModelId()
        )
    }

    private suspend fun getRecipeRequest(json: String): AiRequestDto {
        val prompt = resourceManager.getFileResource(Directories.AI_RECIPE_PROMPT_FILE_PATH)

        return AiRequestDto.makeRequest(
            instructions = prompt,
            text = json,
            imageBase64 = null,
            model = AiConfig.getGptProModelId()
        )
    }
}