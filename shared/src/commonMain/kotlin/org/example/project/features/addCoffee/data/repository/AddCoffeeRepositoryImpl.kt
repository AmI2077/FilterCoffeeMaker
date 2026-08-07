package org.example.project.features.addCoffee.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.extensions.toEntity
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkErrors
import org.example.project.core.data.network.dto.NetworkResult
import org.example.project.core.data.resources.ResourceManager
import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.data.extensions.CoffeeResponseSerializer
import org.example.project.features.addCoffee.domain.AddCoffeeRepository

class AddCoffeeRepositoryImpl(
    private val aiClient: AiClient,
    private val coffeeDao: CoffeeDao,
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatcher
) : AddCoffeeRepository {
    override suspend fun getCoffeeDetailsFromImage(imageBase64: String): AddCoffeeRepositoryResult {
        return withContext(dispatcher) {
            val prompt = resourceManager.getFileResource("files/testCoffeePrompt.txt")

            val result = aiClient.makeRequest(
                AiRequestDto.makeRequest(
                    instructions = prompt,
                    text = "Опиши пачку на изображении",
                    imageBase64 = imageBase64,
                    model = AiConfig.getQwenModelId()
                )
            )

            when (result) {
                is NetworkResult.Error -> {
                    AddCoffeeRepositoryResult.Error(result.error.message)
                }
                is NetworkResult.Success -> {
                    val rawJson = result.data
                    handleSerializeResult(rawJson)
                }
            }
        }
    }

    override suspend fun saveCoffee(coffee: Coffee) {
        withContext(dispatcher) {
            coffeeDao.insertCoffee(coffee.toEntity())
        }
    }

    private fun handleSerializeResult(rawJson: String): AddCoffeeRepositoryResult {
        return try {
            val coffee = Json.decodeFromString(CoffeeResponseSerializer(), rawJson)
                AddCoffeeRepositoryResult.Success(coffee)
        } catch (e: Exception) {
            AddCoffeeRepositoryResult.Error(NetworkErrors.UnknownError.message)
        }
    }
}