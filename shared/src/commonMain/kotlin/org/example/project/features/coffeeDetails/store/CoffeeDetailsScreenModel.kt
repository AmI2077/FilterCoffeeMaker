package org.example.project.features.coffeeDetails.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.ui.store.MviStore
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