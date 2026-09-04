package org.example.project.core.data.network.client

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.example.project.core.data.AiConfig
import org.example.project.core.data.network.dto.AiRequestDto

class YandexAiClient(
    config: AiConfig,
    private val ktorClient: HttpClient
) : AiClient {
    private val yandexCloudFolder: String = config.getYandexCloudFolder()
    private val yandexCloudApiKey: String = config.getApi()
    private val yandexCloudModelBaseUrl: String = config.getYandexCloudBaseUrl()

    override suspend fun makeRequest(request: AiRequestDto): HttpResponse {
        return buildKtorRequest(request)
    }

    private suspend fun buildKtorRequest(request: AiRequestDto): HttpResponse {
        return ktorClient.post(yandexCloudModelBaseUrl) {
            contentType(ContentType.Application.Json)
            headers {
                append(HttpHeaders.Authorization, "Api-Key $yandexCloudApiKey")
                append("OpenAI-Project", yandexCloudFolder)
            }
            setBody(request)
        }
    }
}