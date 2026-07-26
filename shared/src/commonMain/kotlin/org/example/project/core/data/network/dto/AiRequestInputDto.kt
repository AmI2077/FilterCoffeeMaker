package org.example.project.core.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiRequestInputDto(
    @SerialName("role")
    val role: String,
    @SerialName("content")
    val content: List<AiContentDto>
)

@Serializable
data class AiContentDto(
    @SerialName("type")
    val type: String,
    @SerialName("text")
    val text: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("detail")
    val detail: String? = null,
)

