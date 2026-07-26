package org.example.project.core.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestBodyDto(
    @SerialName("model")
    val model: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("max_output_tokens")
    val maxTokens: Int,
    @SerialName("reasoning_effort")
    val reasoning: String,
    @SerialName("input")
    val input: String,
)
