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
import org.example.project.features.coffeeDetails.ui.utils.CoffeeDetailsScreenCallbacks

class CoffeeDetailsScreenModel(
    storeFactory: (scope: CoroutineScope) -> MviStore<
            CoffeeDetailsScreenUiState,
            CoffeeDetailsIntent,
            CoffeeDetailsAction>
) : ScreenModel, CoffeeDetailsScreenCallbacks {
    private val store = storeFactory(screenModelScope)

    val state = store.state
    val uiActions = store.uiActions

    fun loadCoffeeDetails(coffeeId: String) {
        store.onIntent(CoffeeDetailsIntent.LoadCoffeeDetails(coffeeId))
    }

    fun dismissEditBottomSheet() {
        store.onIntent(CoffeeDetailsIntent.DismissEditBottomSheet)
    }

    override fun onSaveDescription(desc: String) {
        store.onIntent(CoffeeDetailsIntent.SaveDescriptionBtnClicked(desc))
    }

    override fun onRecipeBtnClick() {
        store.onIntent(CoffeeDetailsIntent.RecipeBtnClicked)
    }

    override fun onAddDescriptionBtnClick() {
        store.onIntent(CoffeeDetailsIntent.AddDescriptionBtnClicked)
    }

    override fun onCancellationClick() {
        store.onIntent(CoffeeDetailsIntent.CancelDescriptionBtnClicked)
    }

    override fun onEditBtnClick() {
        store.onIntent(CoffeeDetailsIntent.EditBtnClicked)
    }
}