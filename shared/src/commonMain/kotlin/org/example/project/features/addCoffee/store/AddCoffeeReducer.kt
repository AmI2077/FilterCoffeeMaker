package org.example.project.features.addCoffee.store

import org.example.project.core.ui.store.MviReducer

class AddCoffeeReducer: MviReducer<AddCoffeeScreenUiState, AddCoffeeResults> {
    override fun reduce(
        oldState: AddCoffeeScreenUiState,
        result: AddCoffeeResults
    ): AddCoffeeScreenUiState {
        return when(result) {
            is AddCoffeeResults.CoffeeInfoError -> oldState.copy(
                error = result.message
            )
            is AddCoffeeResults.CoffeeInfoSuccess -> oldState.copy(
                coffeeInfo = result.coffeeInfo,
                error = null
            )
            is AddCoffeeResults.ImageLoaded -> oldState.copy(
                imageDirectory = result.imageDirectory,
                imageByteArray = result.imageByteArray,
                imageName = result.imageName
            )
            AddCoffeeResults.Loading -> oldState.copy(
                isLoading = true,
                error = null
            )
        }
    }
}