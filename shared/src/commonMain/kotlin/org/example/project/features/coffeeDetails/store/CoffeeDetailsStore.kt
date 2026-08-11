package org.example.project.features.coffeeDetails.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.emitAction
import org.example.project.core.ui.store.updateState
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository

class CoffeeDetailsStore(
    private val reducer: CoffeeDetailsReducer,
    private val scope: CoroutineScope,
    private val repository: CoffeeDetailsRepository,
    private val imageSaver: ImageSaver
) : MviStore<CoffeeDetailsScreenUiState, CoffeeDetailsIntent, CoffeeDetailsAction> {

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
            is CoffeeDetailsIntent.SaveDescriptionBtnClicked -> saveDescription(intent.description)
        }
    }

    fun loadCoffeeDetails(coffeeId: String) {
        scope.launch {
            repository.getCoffeeDetailsFlow(coffeeId)
                .catch { e -> println("COFFEE_ERROR: $e") }
                .collect { coffee ->
                    val directory = coffee.imagePath?.let {
                        imageSaver.getDirectory(it)
                    }
                    _state.updateState(
                        reducer,
                        CoffeeDetailsResult.CoffeeSuccessLoaded(coffee.copy(imagePath = directory))
                    )
                }
        }
    }

    fun saveDescription(description: String) {
        // TODO "пофиксить nullable coffee, ибо в этот момент он не может быть nullable"
        _state.value.content?.copy(
            userDescription = description
        )?.let {
            _state.updateState(reducer, CoffeeDetailsResult.SaveDescription(it))
        }
    }

    fun onRecipeClick() {
        _uiActions.emitAction(scope = scope, CoffeeDetailsAction.ClickOnRecipeBtn)
    }

    fun onAddDescription() {
        _state.updateState(reducer, CoffeeDetailsResult.ShowDescriptionEditField)
    }

    fun onCancelDescription() {
        _state.updateState(reducer, CoffeeDetailsResult.CancelDescriptionEditField)
    }

    fun dismissEditBottomSheet() {
        _state.updateState(reducer, CoffeeDetailsResult.CloseEditDialog)
    }

    fun onEditButton() {
        _state.updateState(reducer, CoffeeDetailsResult.OpenEditDialog)
    }
}