package org.example.project.core.di

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.ui.store.MviReducer
import org.example.project.features.addCoffee.store.AddCoffeeReducer
import org.example.project.features.addCoffee.store.AddCoffeeResults
import org.example.project.features.addCoffee.store.AddCoffeeScreenUiState
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.savedCoffee.store.SavedCoffeeStore
import org.koin.dsl.module

val storeModule = module {
    factory { (scope: CoroutineScope) ->
        AddCoffeeStore(
            get(),
            get(),
            get(),
            scope,
        )
    }

    factory {  (scope: CoroutineScope) ->
        SavedCoffeeStore(
            get(),
            get(),
            scope
        )
    }

    single<MviReducer<AddCoffeeScreenUiState, AddCoffeeResults>> {
        AddCoffeeReducer()
    }
}