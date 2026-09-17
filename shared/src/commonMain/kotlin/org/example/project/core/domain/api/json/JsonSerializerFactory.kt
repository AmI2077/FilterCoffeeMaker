package org.example.project.core.domain.api.json

import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass

interface JsonSerializerFactory {

    fun <T: Any> create(clazz: KClass<T>, kSerializer: KSerializer<T>): JsonResponseSerializer<T>
}