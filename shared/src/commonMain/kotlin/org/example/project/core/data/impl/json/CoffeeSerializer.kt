package org.example.project.core.data.impl.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.example.project.core.domain.api.json.JsonResponseSerializer
import org.example.project.core.domain.model.Coffee

class CoffeeSerializer(
    private val json: Json,
    private val serializer: KSerializer<Coffee>
): JsonResponseSerializer<Coffee> {

    override fun deserialize(rawJson: String): Coffee {
        return json.decodeFromString(serializer, rawJson)
    }
}