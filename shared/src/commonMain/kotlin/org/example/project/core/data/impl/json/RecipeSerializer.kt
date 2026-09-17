package org.example.project.core.data.impl.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.example.project.core.domain.api.json.JsonResponseSerializer
import org.example.project.core.domain.model.Recipe

class RecipeSerializer(
    private val json: Json,
    private val serializer: KSerializer<Recipe>
): JsonResponseSerializer<Recipe> {
    override fun deserialize(rawJson: String): Recipe {
        return json.decodeFromString(serializer, rawJson)
    }
}