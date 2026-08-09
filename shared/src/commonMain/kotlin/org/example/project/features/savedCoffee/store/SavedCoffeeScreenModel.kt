package org.example.project.features.savedCoffee.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.domain.model.Coffee
import org.example.project.features.savedCoffee.ui.screens.SavedCoffeeDialogResult

class SavedCoffeeScreenModel(
    storeFactory: (CoroutineScope) -> SavedCoffeeStore
) : ScreenModel {
    private val store = storeFactory(screenModelScope)

    val state = store.state

    val uiActions = store.uiActions

    init {
        loadSavedCoffee()
    }

    fun loadSavedCoffee() {
        store.onIntent(SavedCoffeeScreenIntent.LoadSavedCoffee)
    }

    fun onCoffeeItemClick(coffeeId: String) {
        store.onIntent(SavedCoffeeScreenIntent.CoffeeItemClick(coffeeId))
    }

    fun onCoffeeItemLongClick(coffee: Coffee) {
        store.onIntent(SavedCoffeeScreenIntent.CoffeeItemLongClick(coffee))
    }

    fun onRecipeBtnClick(coffeeId: String) {
        store.onIntent(SavedCoffeeScreenIntent.RecipeBtnClick(coffeeId))
    }

    fun onDialogResult(result: SavedCoffeeDialogResult) {
        when(result) {
            is SavedCoffeeDialogResult.Confirm -> {
                store.onIntent(SavedCoffeeScreenIntent.ConfirmDeleteDialog(result.coffee))
            }
            SavedCoffeeDialogResult.Dismiss -> {
                store.onIntent(SavedCoffeeScreenIntent.DismissDeleteDialog)
            }
        }
    }
}