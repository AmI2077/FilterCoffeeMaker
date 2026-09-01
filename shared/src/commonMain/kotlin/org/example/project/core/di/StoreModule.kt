package org.example.project.core.di

import kotlinx.coroutines.CoroutineScope
import org.example.project.features.addCoffee.store.AddCoffeeReducer
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.coffeeDetails.store.CoffeeDetailsReducer
import org.example.project.features.coffeeDetails.store.CoffeeDetailsStore
import org.example.project.features.recipeDetails.store.RecipeDetailsReducer
import org.example.project.features.recipeDetails.store.RecipeDetailsStore
import org.example.project.features.savedCoffee.store.SavedCoffeeStore
import org.koin.dsl.module

val storeModule = module {
    factory { (scope: CoroutineScope) ->
        AddCoffeeStore(
            get(),
            get(),
            get(),
            scope,
            get()
        )
    }

    factory { (scope: CoroutineScope) ->
        CoffeeDetailsStore(
            get(),
            scope,
            get(),
            get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SavedCoffeeStore(
            get(),
            get(),
            scope
        )
    }

    factory { (scope: CoroutineScope) ->
        RecipeDetailsStore(
            get(),
            get(),
            get(),
            get(),
            scope
        )
    }

    single { RecipeDetailsReducer() }
    single { AddCoffeeReducer() }
    single { CoffeeDetailsReducer() }
}