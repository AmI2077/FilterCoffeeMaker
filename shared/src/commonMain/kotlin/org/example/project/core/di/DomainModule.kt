package org.example.project.core.di

import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.impl.AppLoggerImpl
import org.example.project.features.addCoffee.domain.useCases.CoffeeExistUseCase
import org.example.project.features.addCoffee.domain.useCases.CoffeeFromImageUseCase
import org.example.project.features.addCoffee.domain.useCases.SaveCoffeeUseCase
import org.example.project.features.coffeeDetails.domain.EditCoffeeUseCase
import org.example.project.features.coffeeDetails.domain.GetCoffeeDetailsFlowUseCase
import org.example.project.features.coffeeDetails.domain.GetCoffeeDetailsUseCase
import org.example.project.features.recentRecipes.domain.useCases.GetRecentRecipesUseCase
import org.example.project.features.recipeDetails.domain.useCases.GetRecipeUseCase
import org.example.project.features.recipeDetails.domain.useCases.SaveRecipeToFavouritesUseCase
import org.example.project.features.recipeDetails.domain.useCases.SaveRecipeToRecentsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        CoffeeExistUseCase(get())
    }

    factory {
        CoffeeFromImageUseCase(get())
    }

    factory {
        SaveCoffeeUseCase(get())
    }

    factory {
        EditCoffeeUseCase(get())
    }

    factory {
        GetCoffeeDetailsFlowUseCase(get())
    }

    factory {
        GetCoffeeDetailsUseCase(get())
    }

    factory {
        GetRecentRecipesUseCase(get())
    }

    factory {
        GetRecipeUseCase(get())
    }

    factory {
        SaveRecipeToFavouritesUseCase(get())
    }

    factory {
        SaveRecipeToRecentsUseCase(get())
    }

    single<AppLogger> {
        AppLoggerImpl()
    }
}