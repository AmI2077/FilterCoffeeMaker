package org.example.project.features.savedCoffee.ui.states

import org.example.project.core.domain.model.Coffee

data class CoffeeScreenUiState(
    val coffeeList: List<Coffee> = emptyList()
)
