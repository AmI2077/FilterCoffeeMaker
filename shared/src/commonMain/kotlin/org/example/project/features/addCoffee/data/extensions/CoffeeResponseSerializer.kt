package org.example.project.features.addCoffee.data.extensions

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.project.core.domain.model.Coffee

class CoffeeResponseSerializer : KSerializer<Coffee> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Coffee")

    override fun serialize(
        encoder: Encoder,
        value: Coffee
    ) {
        throw UnsupportedOperationException("Операция сериализации не поддерживается")
    }

    override fun deserialize(decoder: Decoder): Coffee {
        val jsonDecoder = decoder as JsonDecoder
        val rootJson = jsonDecoder.decodeJsonElement().jsonObject

        return try {
            val output = rootJson["output"]?.jsonArray
                ?: throw NoSuchElementException("Параметр 'output' отсутствует")

            val obj = output.getOrNull(1)?.jsonObject
                ?: throw NoSuchElementException("Элемент с индексом 1 в массиве output отсутствует")

            val contentArray = obj["content"]?.jsonArray
                ?: throw NoSuchElementException("Параметр 'content' отсутствует")

            val contentObj = contentArray.getOrNull(0)?.jsonObject
                ?: throw NoSuchElementException("Элемент с индексом 0 в массиве content отсутствует")

            val coffeeJson = contentObj["text"]?.jsonPrimitive?.content
                ?: throw NoSuchElementException("Параметр 'text' == null")

            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }

            json.decodeFromString(Coffee.serializer(), cleanMarkdownJson(coffeeJson))

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun cleanMarkdownJson(input: String): String {
        var trimmed = input.trim()

        // Удаляем начальный тег ```json или просто ```
        if (trimmed.startsWith("```json")) {
            trimmed = trimmed.removePrefix("```json").trim()
        } else if (trimmed.startsWith("```")) {
            trimmed = trimmed.removePrefix("```").trim()
        }

        // Удаляем закрывающий тег ```
        if (trimmed.endsWith("```")) {
            trimmed = trimmed.removeSuffix("```").trim()
        }

        return trimmed
    }
}