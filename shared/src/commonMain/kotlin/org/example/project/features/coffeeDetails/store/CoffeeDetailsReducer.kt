package org.example.project.features.coffeeDetails.store

import org.example.project.core.ui.store.MviReducer

class CoffeeDetailsReducer: MviReducer<CoffeeDetailsScreenUiState, CoffeeDetailsResult> {
    override fun reduce(
        oldState: CoffeeDetailsScreenUiState,
        result: CoffeeDetailsResult
    ): CoffeeDetailsScreenUiState {
        return when(result) {
            CoffeeDetailsResult.CancelDescriptionEditField -> oldState.copy(
                showEditDescriptionField = false
            )
            CoffeeDetailsResult.CloseEditDialog -> oldState.copy(
                showEditBottomSheet = false
            )
            is CoffeeDetailsResult.CoffeeSuccessLoaded -> oldState.copy(
                content = result.coffee
            )
            CoffeeDetailsResult.OpenEditDialog -> oldState.copy(
                showEditBottomSheet = true
            )
            is CoffeeDetailsResult.SaveDescription -> oldState.copy(
                content = result.coffeeWithUpdatedDesc,
                showEditDescriptionField = false
            )
            CoffeeDetailsResult.ShowDescriptionEditField -> oldState.copy(
                showEditDescriptionField = true
            )
        }
    }
}