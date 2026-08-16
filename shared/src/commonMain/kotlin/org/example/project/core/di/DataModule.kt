package org.example.project.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.local.db.AppDatabase
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.RecipeDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.client.YandexAiClient
import org.example.project.core.data.resources.ResourceManagerImpl
import org.example.project.core.domain.api.ResourceManager
import org.koin.dsl.module

val dataModule = module {
    includes(repositoryModule)

    single<AiClient> {
        YandexAiClient(get(), get())
    }

    single<ResourceManager> {
        ResourceManagerImpl()
    }

    // TODO "передавать dispatcher через самописный интерфейс"
    single<CoroutineDispatcher> { Dispatchers.IO }

    single<CoffeeDao> { get<AppDatabase>().getCoffeeDao() }
    single<RecipeDao> { get<AppDatabase>().getRecipeDao() }
    single { AiConfig }
    single { Json { ignoreUnknownKeys = true } }
}