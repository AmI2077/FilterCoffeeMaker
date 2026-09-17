package org.example.project.core.di.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.example.project.core.data.impl.json.CoffeeSerializer
import org.example.project.core.data.impl.json.JsonSerializerFactoryImpl
import org.example.project.core.data.impl.json.RecipeSerializer
import org.example.project.core.data.impl.json.kSerializers.CoffeeResponseSerializer
import org.example.project.core.data.impl.json.kSerializers.RecipeResponseSerializer
import org.example.project.core.domain.api.json.JsonResponseSerializer
import org.example.project.core.domain.api.json.JsonSerializerFactory
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe
import org.koin.dsl.module

val jsonModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single<JsonSerializerFactory> {
        JsonSerializerFactoryImpl(get())
    }

    single<JsonResponseSerializer<Coffee>> {
        CoffeeSerializer(
            get(),
            get()
        )
    }

    single<JsonResponseSerializer<Recipe>> {
        RecipeSerializer(
            get(),
            get()
        )
    }

    single<KSerializer<Coffee>> {
        CoffeeResponseSerializer()
    }

    single<KSerializer<Recipe>> {
        RecipeResponseSerializer()
    }
}