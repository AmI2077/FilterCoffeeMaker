package org.example.project.features.coffeeDetails.ui.vm

import org.example.project.core.domain.model.Coffee

sealed interface CoffeeDetailsScreenUiState {

    data class Content(val coffee: Coffee) : CoffeeDetailsScreenUiState
    data object Loading : CoffeeDetailsScreenUiState
}