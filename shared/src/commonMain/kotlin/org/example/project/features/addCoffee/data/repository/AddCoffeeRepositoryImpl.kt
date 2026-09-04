package org.example.project.features.addCoffee.data.repository

import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.domain.model.NetworkErrors
import org.example.project.core.domain.model.NetworkResult
import org.example.project.core.data.resources.Directories
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.api.JsonResponseSerializer
import org.example.project.core.domain.api.NetworkResponseHandler
import org.example.project.core.domain.api.ResourceManager
import org.example.project.core.domain.impl.getWithId
import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.data.extensions.CoffeeResponseSerializer
import org.example.project.features.addCoffee.domain.AddCoffeeRepository

class AddCoffeeRepositoryImpl(
    private val aiClient: AiClient,
    private val coffeeDao: CoffeeDao,
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatchers,
    private val responseHandler: NetworkResponseHandler<HttpResponse>,
) : AddCoffeeRepository<String> {
    override suspend fun getCoffeeDetailsFromImage(imageBase64: String): NetworkResult<String> {
        return withContext(dispatcher.io()) {
            val prompt = resourceManager.getFileResource(Directories.AI_COFFEE_PROMPT_FILE_PATH)

            val result = aiClient.makeRequest(
                AiRequestDto.makeRequest(
                    instructions = prompt,
                    text = "Опиши пачку на изображении",
                    imageBase64 = imageBase64,
                    model = AiConfig.getQwenModelId()
                )
            )
            responseHandler.handle(result,String::class)
        }
    }

    override suspend fun isCoffeeExist(coffee: Coffee): Boolean {
        return coffeeDao.getCoffeeDetails(coffee.id) != null
    }

    override suspend fun saveCoffee(coffee: Coffee) {
        coffeeDao.insertCoffee(coffee.toEntity())
    }

    private fun handleSerializeResult(rawJson: String): AddCoffeeRepositoryResult {
        return try {
            val coffee = Json.decodeFromString(CoffeeResponseSerializer(), rawJson).copy()

            AddCoffeeRepositoryResult.Success(
                coffee.getWithId()
            )
        } catch (e: Exception) {
            AddCoffeeRepositoryResult.Error(NetworkErrors.UnknownError.message)
        }
    }
}