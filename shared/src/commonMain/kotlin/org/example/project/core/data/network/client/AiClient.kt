package org.example.project.core.data.network.client

import org.example.project.core.data.network.dto.AiRequestDto
import org.example.project.core.data.network.dto.NetworkResult

interface AiClient {

    suspend fun makeRequest(
        request: AiRequestDto
    ): NetworkResult<String>
}