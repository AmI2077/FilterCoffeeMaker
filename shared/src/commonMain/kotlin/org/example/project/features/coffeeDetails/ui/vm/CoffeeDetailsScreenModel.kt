package org.example.project.features.coffeeDetails.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository

class CoffeeDetailsScreenModel(
    private val repository: CoffeeDetailsRepository,
    private val imageSaver: ImageSaver
) : ScreenModel {
    private var _state = MutableStateFlow(CoffeeDetailsScreenUiState())
    val state = _state.asStateFlow()

    private var _uiActions = MutableSharedFlow<CoffeeDetailsAction>()
    val uiActions = _uiActions.asSharedFlow()

    fun onIntent(intent: CoffeeDetailsIntent) {
        when (intent) {
            CoffeeDetailsIntent.AddDescriptionBtnClicked -> updateState(
                showEditDescriptionField = true
            )
            CoffeeDetailsIntent.CancelDescriptionBtnClicked -> updateState(
                showEditDescriptionField = false
            )
            CoffeeDetailsIntent.RecipeBtnClicked -> emitAction(CoffeeDetailsAction.ClickOnRecipeBtn)

            is CoffeeDetailsIntent.SaveDescriptionBtnClicked -> updateState(
                content = _state.value.content?.copy(
                    userDescription = intent.description
                ),
                showEditDescriptionField = false
            )
            is CoffeeDetailsIntent.LoadCoffeeDetails -> loadCoffeeDetails(intent.coffeeId)
        }
    }

    private fun loadCoffeeDetails(coffeeId: String) {
        screenModelScope.launch {
            val coffee = repository.getCoffeeDetails(coffeeId)
            // TODO "Реализовать отображение ошибки, если из базы данных приходит null"
            if (coffee != null) {
                val directory = coffee.imagePath?.let {
                    imageSaver.getDirectory(it)
                }
                updateState(
                    content = coffee.copy(
                        imagePath = directory
                    )
                )
            } else {
                println("COFFEE_DETAILS: $coffee")
            }
        }
    }

    private fun updateState(
        content: Coffee? = null,
        showEditDescriptionField: Boolean? = null
    ) {
        _state.update { oldState ->
            CoffeeDetailsScreenUiState(
                content = content ?: oldState.content,
                showEditDescriptionField = showEditDescriptionField
                    ?: oldState.showEditDescriptionField
            )
        }
    }

    private fun emitAction(action: CoffeeDetailsAction) {
        screenModelScope.launch {
            _uiActions.emit(action)
        }
    }
}