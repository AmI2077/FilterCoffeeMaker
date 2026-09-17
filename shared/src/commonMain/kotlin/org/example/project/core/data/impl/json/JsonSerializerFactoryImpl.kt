package org.example.project.core.data.impl.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.example.project.core.domain.api.json.JsonResponseSerializer
import org.example.project.core.domain.api.json.JsonSerializerFactory
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe
import kotlin.reflect.KClass

class JsonSerializerFactoryImpl(
    private val json: Json
) : JsonSerializerFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> create(
        clazz: KClass<T>,
        kSerializer: KSerializer<T>
    ): JsonResponseSerializer<T> {
        return when (clazz) {
            Coffee::class -> {
                CoffeeSerializer(
                    json,
                    kSerializer as KSerializer<Coffee>
                ) as JsonResponseSerializer<T>
            }

            Recipe::class -> {
                RecipeSerializer(
                    json,
                    kSerializer as KSerializer<Recipe>
                ) as JsonResponseSerializer<T>
            }

            else -> throw IllegalStateException("Неизвестный класс: $clazz")
        }
    }
}