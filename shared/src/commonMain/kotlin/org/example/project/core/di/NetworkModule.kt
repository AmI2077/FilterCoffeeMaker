package org.example.project.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.client.YandexAiClient
import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.api.LogMessageType
import org.example.project.core.domain.api.log
import org.koin.dsl.module

val networkModule = module {

    single<AiClient> {
        YandexAiClient(get(), get())
    }

    single<AiConfig> { AiConfig }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single<Logger> {
        object : Logger {
            override fun log(message: String) {
                get<AppLogger>().log<Logger>(
                    type = LogMessageType.INFO,
                    message = message
                )
            }
        }
    }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = get<Json>()
                )
            }
            install(Logging) {
                logger = get<Logger>()
                level = io.ktor.client.plugins.logging.LogLevel.ALL
            }
            install(HttpTimeout) {
                socketTimeoutMillis = get<AiConfig>().getTimeoutMillis()
            }
        }
    }
}