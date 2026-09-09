package org.example.project.features.coffeeDetails.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.impl.getWithImageDirectory
import org.example.project.core.domain.impl.runIfExist
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.emitAction
import org.example.project.core.ui.store.updateStateWithReducer
import org.example.project.features.coffeeDetails.domain.EditCoffeeUseCase
import org.example.project.features.coffeeDetails.domain.GetCoffeeDetailsFlowUseCase
import org.example.project.features.coffeeDetails.domain.GetCoffeeDetailsUseCase

class CoffeeDetailsStore(
    private val reducer: CoffeeDetailsReducer,
    private val getCoffeeDetailsUseCase: GetCoffeeDetailsUseCase,
    private val getCoffeeDetailsFlowUseCase: GetCoffeeDetailsFlowUseCase,
    private val editCoffeeUseCase: EditCoffeeUseCase,
    private val scope: CoroutineScope,
    private val imageSaver: ImageSaver,
) : MviStore<CoffeeDetailsScreenUiState, CoffeeDetailsIntent, CoffeeDetailsAction> {

    private var imageName: String? = null

    private var _state = MutableStateFlow(CoffeeDetailsScreenUiState())

    private var _uiActions = MutableSharedFlow<CoffeeDetailsAction>()

    override val state: StateFlow<CoffeeDetailsScreenUiState>
        get() = _state

    override val uiActions: SharedFlow<CoffeeDetailsAction>
        get() = _uiActions

    override fun onIntent(intent: CoffeeDetailsIntent) {
        when (intent) {
            CoffeeDetailsIntent.AddDescriptionBtnClicked -> onAddDescription()
            CoffeeDetailsIntent.CancelDescriptionBtnClicked -> onCancelDescription()
            CoffeeDetailsIntent.DismissEditBottomSheet -> dismissEditBottomSheet()
            CoffeeDetailsIntent.EditBtnClicked -> onEditButton()
            is CoffeeDetailsIntent.LoadCoffeeDetails -> loadCoffeeDetails(intent.coffeeId)
            CoffeeDetailsIntent.RecipeBtnClicked -> onRecipeClick()
            is CoffeeDetailsIntent.SaveDescriptionBtnClicked -> onSaveDescriptionClick(intent.description)
        }
    }

    fun loadCoffeeDetails(coffeeId: String) {
        scope.launch {
            getCoffeeDetailsFlowUseCase(coffeeId)
                .catch { e -> println("COFFEE_ERROR: $e") }
                .collect { coffee ->
                    imageName = coffee.imagePath

                    val newCoffee = coffee.getWithImageDirectory(imageSaver)

                    _state.updateStateWithReducer(
                        reducer,
                        CoffeeDetailsResult.CoffeeSuccessLoaded(newCoffee)
                    )
                }
        }
    }

    fun onSaveDescriptionClick(description: String) {
        runIfExist(_state.value::content) { coffee ->
            val updatedCoffee = updateCoffeeDesc(coffee, description)

            saveDescription(updatedCoffee)
        }
    }

    private fun saveDescription(coffeeWithDesc: Coffee) {
        scope.launch {
            _state.updateStateWithReducer(reducer, CoffeeDetailsResult.SaveDescription(coffeeWithDesc))
            editCoffeeUseCase(coffeeWithDesc)
        }
    }

    private fun updateCoffeeDesc(coffee: Coffee, newDesc: String): Coffee {
        return coffee.copy(
            imagePath = imageName,
            userDescription = newDesc
        )
    }

    fun onRecipeClick() {
        _uiActions.emitAction(scope = scope, CoffeeDetailsAction.ClickOnRecipeBtn)
    }

    fun onAddDescription() {
        _state.updateStateWithReducer(reducer, CoffeeDetailsResult.ShowDescriptionEditField)
    }

    fun onCancelDescription() {
        _state.updateStateWithReducer(reducer, CoffeeDetailsResult.CancelDescriptionEditField)
    }

    fun dismissEditBottomSheet() {
        _state.updateStateWithReducer(reducer, CoffeeDetailsResult.CloseEditDialog)
    }

    fun onEditButton() {
        _state.updateStateWithReducer(reducer, CoffeeDetailsResult.OpenEditDialog)
    }
}