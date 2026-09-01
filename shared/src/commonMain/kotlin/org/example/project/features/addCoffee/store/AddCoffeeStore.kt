package org.example.project.features.addCoffee.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.api.LogMessageType
import org.example.project.core.domain.api.log
import org.example.project.core.domain.impl.runIfExist
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.emitAction
import org.example.project.core.ui.store.updateStateWithReducer
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryResult
import org.example.project.features.addCoffee.domain.AddCoffeeInteractor

class AddCoffeeStore(
    private val reducer: AddCoffeeReducer,
    private val addCoffeeInteractor: AddCoffeeInteractor,
    private val imageSaver: ImageSaver,
    private val scope: CoroutineScope,
    private val logger: AppLogger
) : MviStore<AddCoffeeScreenUiState, AddCoffeeIntent, AddCoffeeActions> {
    private var _state = MutableStateFlow(AddCoffeeScreenUiState())
    override val state = _state.asStateFlow()

    private var _uiActions = MutableSharedFlow<AddCoffeeActions>()
    override val uiActions = _uiActions.asSharedFlow()

    override fun onIntent(intent: AddCoffeeIntent) {
        when (intent) {
            is AddCoffeeIntent.LoadCoffeeInfo -> {
                runIfExist(_state.value::imageByteArray, logger) {
                    loadCoffeeInfo(it)
                }
            }

            AddCoffeeIntent.PickImage -> pickImage()

            is AddCoffeeIntent.ImagePicked -> loadPickedImage(
                imageName = intent.imageName,
                imageByteArray = intent.imageByteArray
            )

            is AddCoffeeIntent.AddCoffeeBtnClicked -> {
                runIfExist(_state.value::coffeeInfo, logger) {
                    onAddCoffeeBtnClicked(it)
                }
            }

            AddCoffeeIntent.ConfirmAlreadyExistDialog -> {
                runIfExist(_state.value::coffeeInfo, logger) {
                    addCoffee(it)
                }
            }

            AddCoffeeIntent.DismissAlreadyExistDialog -> _state.updateStateWithReducer(
                reducer,
                AddCoffeeResults.CloseCoffeeAlreadyExistDialog
            )
        }
    }

    private fun loadCoffeeInfo(imageByteArray: ByteArray) {
        _state.updateStateWithReducer(reducer, result = AddCoffeeResults.Loading)

        scope.launch {
            when (val result = addCoffeeInteractor.getCoffeeDetailsFromImage(imageByteArray)) {
                is AddCoffeeRepositoryResult.Error -> {
                    _state.updateStateWithReducer(
                        reducer,
                        result = AddCoffeeResults.CoffeeInfoError(result.errorMessage)
                    )
                }

                is AddCoffeeRepositoryResult.Success -> {
                    _state.updateStateWithReducer(
                        reducer,
                        result = AddCoffeeResults.CoffeeInfoSuccess(result.coffee)
                    )
                }
            }
        }
    }

    private fun loadPickedImage(
        imageName: String?,
        imageByteArray: ByteArray
    ) {
        scope.launch {
            val imageDirectory = imageName?.let {
                imageSaver.saveImage(
                    name = it,
                    fileBytes = imageByteArray
                )
            }
            _state.updateStateWithReducer(
                reducer,
                result = AddCoffeeResults.ImageLoaded(
                    imageByteArray = imageByteArray,
                    imageDirectory = imageDirectory,
                    imageName = imageName
                )
            )
        }
    }

    private fun onAddCoffeeBtnClicked(coffee: Coffee) {
        scope.launch {
            val isExist = addCoffeeInteractor.isCoffeeExist(coffee)
            if (isExist) {
                _state.updateStateWithReducer(
                    reducer,
                    AddCoffeeResults.ShowCoffeeAlreadyExistDialog
                )
            } else {
                addCoffee(coffee)
            }
        }
    }

    private fun addCoffee(coffeeInfo: Coffee) {
        val coffee = coffeeInfo.copy(
            imagePath = _state.value.imageName
        )
        scope.launch {
            addCoffeeInteractor.saveCoffee(coffee)
        }

        _state.updateStateWithReducer(reducer, AddCoffeeResults.CloseCoffeeAlreadyExistDialog)
        _uiActions.emitAction(scope, AddCoffeeActions.AddCoffeeBtnClicked)
    }

    private fun pickImage() {
        _uiActions.emitAction(scope, AddCoffeeActions.OpenGallery)
    }
}