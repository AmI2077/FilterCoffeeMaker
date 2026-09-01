package org.example.project.core.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.example.project.core.data.AiConfig
import org.example.project.core.data.impl.AndroidCoroutineDispatchers
import org.example.project.core.data.local.db.AppDatabase
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.FavouritesRecipesDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.data.network.client.AiClient
import org.example.project.core.data.network.client.YandexAiClient
import org.example.project.core.data.resources.ResourceManagerImpl
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.api.ResourceManager
import org.koin.dsl.module

val dataModule = module {
    includes(repositoryModule)
    includes(networkModule)

    single<ResourceManager> {
        ResourceManagerImpl()
    }

    single<CoroutineDispatchers> {
        AndroidCoroutineDispatchers(
            io = Dispatchers.IO,
            main = Dispatchers.Main,
            default = Dispatchers.Default,
            unconfined = Dispatchers.Unconfined
        )
    }

    single<CoffeeDao> { get<AppDatabase>().getCoffeeDao() }
    single<FavouritesRecipesDao> { get<AppDatabase>().getFavouritesDao() }
    single<RecentRecipesDao> { get<AppDatabase>().getRecipeDao() }
}