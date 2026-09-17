package org.example.project.core.di.data

import org.example.project.core.data.impl.repository.AiClientRepositoryImpl
import org.example.project.core.data.impl.repository.CoffeeRepositoryImpl
import org.example.project.core.data.impl.repository.RecipesRepositoryImpl
import org.example.project.core.domain.api.ResourceManager
import org.example.project.core.domain.api.repository.AiClientRepository
import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.api.repository.RecipesRepository
import org.example.project.features.recipeDetails.data.repository.LoaderScreenRepositoryImpl
import org.example.project.features.recipeDetails.domain.api.LoaderScreenRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CoffeeRepository> {
        CoffeeRepositoryImpl(get())
    }

    single<AiClientRepository> {
        AiClientRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
        )
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<LoaderScreenRepository> {
        LoaderScreenRepositoryImpl(get<ResourceManager>(), get())
    }
}