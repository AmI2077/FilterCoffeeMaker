package org.example.project.features.newCoffee.ui.states

sealed interface AddCoffeeScreenUiEvents {
    object PickPhoto : AddCoffeeScreenUiEvents
    object NavigateToCoffeeScreen : AddCoffeeScreenUiEvents
    data class ShowAiError(val message: String) : AddCoffeeScreenUiEvents
}