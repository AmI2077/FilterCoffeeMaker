package org.example.project.core.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiRequestDto(
    @SerialName("model")
    val model: String,
    @SerialName("instructions")
    val instructions: String,
    @SerialName("input")
    val input: List<AiRequestInputDto>,
) {
    companion object {
        fun makeRequest(
            instructions: String,
            text: String,
            imageBase64: String?,
            model: String,
        ): AiRequestDto {
            return AiRequestDto(
                model = model,
                instructions = instructions,
                input = listOf(
                    AiRequestInputDto(
                        role = "user",
                        content = if (imageBase64 != null) {
                            listOf(
                                AiContentDto(
                                    type = "input_text",
                                    text = text
                                ),
                                AiContentDto(
                                    type = "input_image",
                                    imageUrl = "data:image/png;base64,$imageBase64",
                                    detail = "auto"
                                )
                            )
                        } else {
                            listOf(
                                AiContentDto(
                                    type = "input_text",
                                    text = text
                                ),
                            )
                        }
                    )
                )
            )
        }
    }
}

