package org.example.project.core.data.network.client

import io.ktor.client.statement.HttpResponse
import org.example.project.core.data.network.dto.AiRequestDto

interface AiClient {

    suspend fun makeRequest(
        request: AiRequestDto
    ): HttpResponse
}