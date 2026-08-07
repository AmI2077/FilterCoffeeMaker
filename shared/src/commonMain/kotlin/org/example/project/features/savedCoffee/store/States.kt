package org.example.project.features.savedCoffee.store

import org.example.project.core.domain.model.Coffee

data class SavedCoffeeScreenUiState(
    val savedCoffee: List<Coffee> = emptyList(),
    val showDialog: ShowDialog? = null,
    val isError: String? = null
) {
    companion object {
        const val ERROR_MESSAGE = "Не получилось загрузить пачки("
    }
}

data class ShowDialog(
    val coffee: Coffee
)

sealed interface SavedCoffeeScreenActions {
    data class CoffeeItemClicked(val coffeeId: Int): SavedCoffeeScreenActions
    data class RecipeBtnClicked(val coffeeId: Int): SavedCoffeeScreenActions
}

sealed interface SavedCoffeeScreenIntent {
    data object LoadSavedCoffee: SavedCoffeeScreenIntent
    data class CoffeeItemClick(val coffeeId: Int): SavedCoffeeScreenIntent
    data class CoffeeItemLongClick(val coffee: Coffee): SavedCoffeeScreenIntent
    data class RecipeBtnClick(val coffeeId: Int): SavedCoffeeScreenIntent

    data class ConfirmDeleteDialog(val coffee: Coffee): SavedCoffeeScreenIntent
    data object DismissDeleteDialog: SavedCoffeeScreenIntent
}