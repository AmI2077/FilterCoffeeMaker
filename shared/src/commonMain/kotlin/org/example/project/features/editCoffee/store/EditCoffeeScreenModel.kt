package org.example.project.features.editCoffee.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.model.Coffee
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository

class EditCoffeeScreenModel(
    private val coffeeRepository: CoffeeDetailsRepository
) : ScreenModel {
    private var _state = MutableStateFlow(EditCoffeeUiState())
    val state = _state.asStateFlow()

    fun loadCoffee(coffeeId: String) {
        screenModelScope.launch {
            coffeeRepository.getCoffeeDetailsFlow(coffeeId)
                .collect { coffee ->
                    _state.update {
                        EditCoffeeUiState(coffee)
                    }
                }
        }
    }

    fun onSaveClick(editedCoffee: Coffee) {
        println("IMAGE_PATH: ${editedCoffee.imagePath}")
        screenModelScope.launch {
            coffeeRepository.editCoffee(editedCoffee)
        }
    }
}