package org.example.project.core.di

import org.example.project.core.domain.api.ResourceManager
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryImpl
import org.example.project.features.addCoffee.domain.AddCoffeeRepository
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepositoryImpl
import org.example.project.features.recipeDetails.data.repository.LoaderScreenRepositoryImpl
import org.example.project.features.recipeDetails.data.repository.RecipeDetailsRepositoryImpl
import org.example.project.features.recipeDetails.domain.api.LoaderScreenRepository
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipesList.data.repository.RecipesRepositoryImpl
import org.example.project.features.recipesList.domain.api.RecipesRepository
import org.example.project.features.savedCoffee.data.repository.CoffeeRepositoryImpl
import org.example.project.features.savedCoffee.domain.api.CoffeeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AddCoffeeRepository> {
        AddCoffeeRepositoryImpl(get(), get(), get<ResourceManager>(), get())
    }

    single<RecipeDetailsRepository> {
        RecipeDetailsRepositoryImpl(get(), get(), get<ResourceManager>(), get())
    }

    single<CoffeeRepository> {
        CoffeeRepositoryImpl(get(), get())
    }

    single<CoffeeDetailsRepository> {
        CoffeeDetailsRepositoryImpl(get(), get())
    }

    single<LoaderScreenRepository> {
        LoaderScreenRepositoryImpl(get<ResourceManager>(), get())
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }
}