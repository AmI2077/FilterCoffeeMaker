package org.example.project.features.savedCoffee.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.domain.model.Coffee
import org.example.project.features.savedCoffee.ui.components.DialogResult

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

    fun onCoffeeItemClick(coffeeId: Int) {
        store.onIntent(SavedCoffeeScreenIntent.CoffeeItemClick(coffeeId))
    }

    fun onCoffeeItemLongClick(coffee: Coffee) {
        store.onIntent(SavedCoffeeScreenIntent.CoffeeItemLongClick(coffee))
    }

    fun onRecipeBtnClick(coffeeId: Int) {
        store.onIntent(SavedCoffeeScreenIntent.RecipeBtnClick(coffeeId))
    }

    fun onDialogResult(result: DialogResult) {
        when(result) {
            is DialogResult.Confirm -> {
                store.onIntent(SavedCoffeeScreenIntent.ConfirmDeleteDialog(result.coffee))
            }
            DialogResult.Dismiss -> {
                store.onIntent(SavedCoffeeScreenIntent.DismissDeleteDialog)
            }
        }
    }
}