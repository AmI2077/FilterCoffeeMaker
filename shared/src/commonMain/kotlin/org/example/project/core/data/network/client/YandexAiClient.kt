package org.example.project.core.data.network.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import org.example.project.core.data.AiConfig
import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkErrors
import org.example.project.core.data.network.dto.NetworkResult
import kotlin.coroutines.cancellation.CancellationException

class YandexAiClient(
    config: AiConfig,
    private val ktorClient: HttpClient
) : AiClient {
    private val yandexCloudFolder: String = config.getYandexCloudFolder()
    private val yandexCloudApiKey: String = config.getApi()
    private val yandexCloudModelBaseUrl: String = config.getYandexCloudBaseUrl()

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
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            NetworkResult.Error(NetworkErrors.UnknownError)
        }
    }

    private suspend fun handleResponse(response: HttpResponse): NetworkResult<String> {
        println("RESPONSE_STATUS: \nCODE: ${response.status.value}, ${response.status.description}\nDESCRIPTION: ${response.bodyAsText()}")

        return when (response.status) {
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