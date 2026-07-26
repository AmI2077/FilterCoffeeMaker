package org.example.project.core.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiResponseDto(
    @SerialName("id")
    val id: String,
    @SerialName("object")
    val objectType: String,
    @SerialName("created_at")
    val createdAt: Double? = null,
    @SerialName("error")
    val error: String? = null,
    @SerialName("model")
    val model: String,
    @SerialName("status")
    val status: String? = null,
    @SerialName("output")
    val output: List<AiResponseOutputDto>? = null,
)

@Serializable
data class AiResponseOutputDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("content")
    val content: List<AiResponseOutputContentDto>? = null
)

@Serializable
data class AiResponseOutputContentDto(
    @SerialName("text")
    val text: String? = null
)



