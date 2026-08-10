package org.example.project.features.coffeeDetails.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.emitAction
import org.example.project.core.ui.store.updateState
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository

class CoffeeDetailsScreenModel(
    storeFactory: (scope: CoroutineScope) -> MviStore<
            CoffeeDetailsScreenUiState,
            CoffeeDetailsIntent,
            CoffeeDetailsAction>
) : ScreenModel {
    private val store = storeFactory(screenModelScope)

    val state = store.state
    val uiActions = store.uiActions

    fun loadCoffeeDetails(coffeeId: String) {
        store.onIntent(CoffeeDetailsIntent.LoadCoffeeDetails(coffeeId))
    }

    fun saveDescription(description: String) {
        store.onIntent(CoffeeDetailsIntent.SaveDescriptionBtnClicked(description))
    }

    fun onRecipeClick() {
        store.onIntent(CoffeeDetailsIntent.RecipeBtnClicked)
    }

    fun onAddDescription() {
        store.onIntent(CoffeeDetailsIntent.AddDescriptionBtnClicked)
    }

    fun onCancelDescription() {
        store.onIntent(CoffeeDetailsIntent.CancelDescriptionBtnClicked)
    }

    fun dismissEditBottomSheet() {
        store.onIntent(CoffeeDetailsIntent.DismissEditBottomSheet)
    }

    fun onEditButton() {
        store.onIntent(CoffeeDetailsIntent.EditBtnClicked)
    }
}