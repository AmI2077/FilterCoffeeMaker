package org.example.project.features.savedCoffee.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.store.MviStore
import org.example.project.features.savedCoffee.domain.api.CoffeeInteractor

class SavedCoffeeStore(
    private val coffeeInteractor: CoffeeInteractor,
    private val imageSaver: ImageSaver,
    private val scope: CoroutineScope,
) : MviStore<SavedCoffeeScreenUiState, SavedCoffeeScreenIntent, SavedCoffeeScreenActions> {
    private var _state = MutableStateFlow(SavedCoffeeScreenUiState())
    override val state: StateFlow<SavedCoffeeScreenUiState> = _state.asStateFlow()

    private var _uiActions = MutableSharedFlow<SavedCoffeeScreenActions>()
    override val uiActions: SharedFlow<SavedCoffeeScreenActions> = _uiActions.asSharedFlow()

    override fun onIntent(intent: SavedCoffeeScreenIntent) {
        when (intent) {
            is SavedCoffeeScreenIntent.CoffeeItemClick -> onCoffeeItemClick(intent.coffeeId)

            is SavedCoffeeScreenIntent.CoffeeItemLongClick -> onCoffeeItemLongClick(intent.coffee)

            is SavedCoffeeScreenIntent.RecipeBtnClick -> onRecipeBtnClick(intent.coffeeId)

            SavedCoffeeScreenIntent.LoadSavedCoffee -> loadSavedCoffee()

            is SavedCoffeeScreenIntent.ConfirmDeleteDialog -> {
                deleteCoffee(intent.coffee)
                println("DELETED_COFFEE: ${intent.coffee}")
                updateState(showDialog = null)
            }
            SavedCoffeeScreenIntent.DismissDeleteDialog -> {
                println("DISMISS_DIALOG")
                updateState(showDialog = null)
            }
        }
    }

    private fun onCoffeeItemClick(coffeeId: String) {
        emitAction(SavedCoffeeScreenActions.CoffeeItemClicked(coffeeId))
    }

    private fun onCoffeeItemLongClick(coffee: Coffee) {
        updateState(
            showDialog = ShowDialog(coffee = coffee)
        )
    }

    private fun onRecipeBtnClick(coffeeId: String) {
        emitAction(SavedCoffeeScreenActions.RecipeBtnClicked(coffeeId))
    }

    private fun loadSavedCoffee() {
        scope.launch {
            coffeeInteractor.getCoffeeList()
                .map { coffeeList ->
                    coffeeList.map { coffee ->
                        coffee.imagePath?.let {
                            coffee.copy(
                                imagePath = imageSaver.getDirectory(coffee.imagePath)
                            )
                        } ?: coffee
                    }
                }
                .catch {
                    updateState(emptyList(), SavedCoffeeScreenUiState.ERROR_MESSAGE)
                }
                .collect { coffeeList ->
                    updateState(coffeeList, null)
                }
        }
    }

    private fun deleteCoffee(coffee: Coffee) {
        scope.launch {
            coffeeInteractor.deleteCoffee(coffee)
        }
    }

    private fun emitAction(action: SavedCoffeeScreenActions) {
        scope.launch {
            _uiActions.emit(action)
        }
    }

    private fun updateState(
        savedCoffee: List<Coffee>? = null,
        error: String? = null,
        showDialog: ShowDialog? = null,
    ) {
        _state.update {
            it.copy(
                savedCoffee = savedCoffee ?: it.savedCoffee,
                isError = error,
                showDialog = showDialog
            )
        }
    }
}