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
import org.example.project.core.data.resources.ResourceManager
import org.example.project.core.data.resources.ResourceManagerImpl
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepositoryImpl
import org.example.project.features.savedCoffee.data.repository.CoffeeRepositoryImpl
import org.example.project.features.savedCoffee.domain.api.CoffeeRepository
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryImpl
import org.example.project.features.addCoffee.domain.AddCoffeeRepository
import org.example.project.features.recipeDetails.data.repository.LoaderScreenRepositoryImpl
import org.example.project.features.recipeDetails.data.repository.RecipeDetailsRepositoryImpl
import org.example.project.features.recipeDetails.domain.api.LoaderScreenRepository
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipesList.data.repository.RecipesRepositoryImpl
import org.example.project.features.recipesList.domain.api.RecipesRepository
import org.koin.dsl.module

val dataModule = module {
    single<AiClient> {
        YandexAiClient(get(), get())
    }

    single<ResourceManager> {
        ResourceManagerImpl()
    }

    single<CoroutineDispatcher> { Dispatchers.IO }

    single<AddCoffeeRepository> {
        AddCoffeeRepositoryImpl(get(), get(), get(), get())
    }

    single<RecipeDetailsRepository> {
        RecipeDetailsRepositoryImpl(get(), get(), get(), get())
    }

    single<CoffeeRepository> {
        CoffeeRepositoryImpl(get())
    }

    single<CoffeeDetailsRepository> {
        CoffeeDetailsRepositoryImpl(get(), get())
    }

    single<LoaderScreenRepository> {
        LoaderScreenRepositoryImpl(get(), get())
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }

    single<CoffeeDao> { get<AppDatabase>().getCoffeeDao() }
    single<RecipeDao> { get<AppDatabase>().getRecipeDao() }
    single { AiConfig }
    single { Json { ignoreUnknownKeys = true } }
}