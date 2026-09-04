package org.example.project.features.addCoffee.data.impl

import kotlinx.serialization.json.Json
import org.example.project.core.domain.api.JsonResponseSerializer
import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.data.extensions.CoffeeResponseSerializer

class CoffeeSerializer(
    private val serializer: CoffeeResponseSerializer,
    private val json: Json
): JsonResponseSerializer<Coffee> {

    override fun serialize(rawJson: String): Coffee {
        return json.decodeFromString(serializer, rawJson)
    }
}