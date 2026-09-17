package org.example.project.features.coffeeDetails.store

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.data.impl.BaseScreenModel
import org.example.project.core.ui.store.MviStore
import org.example.project.features.coffeeDetails.ui.utils.CoffeeDetailsScreenCallbacks

class CoffeeDetailsScreenModel(
    storeFactory: (scope: CoroutineScope) -> MviStore<
            CoffeeDetailsScreenUiState,
            CoffeeDetailsIntent,
            CoffeeDetailsAction>
) : BaseScreenModel(), CoffeeDetailsScreenCallbacks {
    private val store = storeFactory(screenModelScopeWithHandler)

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