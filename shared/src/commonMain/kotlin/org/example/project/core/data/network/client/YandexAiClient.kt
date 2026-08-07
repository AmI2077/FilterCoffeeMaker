package org.example.project.core.data.network.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkErrors
import org.example.project.core.data.network.dto.NetworkResult

class YandexAiClient(
    private val config: AiConfig,
    private val json: Json
) : AiClient {

    private val timeOutMillis: Long = 300000

    private val temperature = config.getTemperature()
    private val maxTokens = config.getMaxTokens()
    private val reasoningEffort = config.getReasoningEffort()

    private val yandexCloudFolder: String = config.getYandexCloudFolder()
    private val yandexCloudApiKey: String = config.getApi()
    private val yandexCloudModelBaseUrl: String = config.getYandexCloudBaseUrl()

    private val qwenModelId = AiConfig.getQwenModelId()
    private val gtpProModelId = AiConfig.getGptProModelId()

    private val ktorClient = HttpClient {
        install(ContentNegotiation) {
            json(
                json = json
            )
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                },
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("KTOR_LOG: $message")
                }
            }
            level = io.ktor.client.plugins.logging.LogLevel.ALL
        }
        install(HttpTimeout) {
            socketTimeoutMillis = timeOutMillis
        }
    }

    override suspend fun makeRequest(request: AiRequestDto): NetworkResult<String> {
        return try {
            val response = ktorClient.post(yandexCloudModelBaseUrl) {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Api-Key $yandexCloudApiKey")
                    append("OpenAI-Project", yandexCloudFolder)
                }
                setBody(request)
            }
            handleResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()

            NetworkResult.Error(NetworkErrors.UnknownError)
        }
    }

    private suspend fun handleResponse(response: HttpResponse): NetworkResult<String> {
        println("RESPONSE_STATUS: \nCODE: ${response.status.value}, ${response.status.description}\nDESCRIPTION: ${response.bodyAsText()}")

        return when (val status = response.status) {
            HttpStatusCode.BadGateway -> NetworkResult.Error(NetworkErrors.BadGateway)
            HttpStatusCode.GatewayTimeout -> NetworkResult.Error(NetworkErrors.GatewayTimeout)
            HttpStatusCode.InternalServerError -> NetworkResult.Error(NetworkErrors.InternalServerError)
            HttpStatusCode.OK -> NetworkResult.Success(response.body<String>())
            else -> {
                NetworkResult.Error(NetworkErrors.UnknownError)
            }
        }
    }
}