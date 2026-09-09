package org.example.project.features.editCoffee.store

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.data.impl.BaseScreenModel
import org.example.project.core.domain.model.Coffee
import org.example.project.features.coffeeDetails.domain.EditCoffeeUseCase
import org.example.project.features.coffeeDetails.domain.GetCoffeeDetailsFlowUseCase

class EditCoffeeScreenModel(
    private val editCoffeeUseCase: EditCoffeeUseCase,
    private val getCoffeeDetailsFlowUseCase: GetCoffeeDetailsFlowUseCase,
) : BaseScreenModel() {
    private var _state = MutableStateFlow(EditCoffeeUiState())
    val state = _state.asStateFlow()

    fun loadCoffee(coffeeId: String) {
        screenModelScopeWithHandler.launch {
            getCoffeeDetailsFlowUseCase(coffeeId)
                .collect { coffee ->
                    _state.update {
                        EditCoffeeUiState(coffee)
                    }
                }
        }
    }

    fun onSaveClick(editedCoffee: Coffee) {
        println("IMAGE_PATH: ${editedCoffee.imagePath}")
        screenModelScopeWithHandler.launch {
            editCoffeeUseCase(editedCoffee)
        }
    }
}