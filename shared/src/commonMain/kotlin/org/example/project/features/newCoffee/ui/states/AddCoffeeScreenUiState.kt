package org.example.project.features.newCoffee.ui.states

import org.example.project.core.domain.model.Coffee

data class AddCoffeeScreenUiState(
    val imageName: String? = null,
    val imageDirectory: String? = null,
    val imageByteArray: ByteArray? = null,
    val status: AddCoffeeScreenUiStatus = AddCoffeeScreenUiStatus.Idle,
)

sealed interface AddCoffeeScreenUiStatus {
    object Idle : AddCoffeeScreenUiStatus
    data object Loading : AddCoffeeScreenUiStatus
    data class Error(val message: String) : AddCoffeeScreenUiStatus
    data class Content(val coffee: Coffee) : AddCoffeeScreenUiStatus
    data object PhotoLoaded : AddCoffeeScreenUiStatus
}

