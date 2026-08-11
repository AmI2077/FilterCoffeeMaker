package org.example.project.features.addCoffee.store

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
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryResult
import org.example.project.features.addCoffee.domain.AddCoffeeInteractor

class AddCoffeeStore(
    private val reducer: AddCoffeeReducer,
    private val addCoffeeInteractor: AddCoffeeInteractor,
    private val imageSaver: ImageSaver,
    private val scope: CoroutineScope
) : MviStore<AddCoffeeScreenUiState, AddCoffeeIntent, AddCoffeeActions> {
    private var _state = MutableStateFlow(AddCoffeeScreenUiState())
    override val state = _state.asStateFlow()

    private var _uiActions = MutableSharedFlow<AddCoffeeActions>()
    override val uiActions = _uiActions.asSharedFlow()

    override fun onIntent(intent: AddCoffeeIntent) {
        when (intent) {
            is AddCoffeeIntent.LoadCoffeeInfo -> loadCoffeeInfo(intent.imageByteArray)

            AddCoffeeIntent.PickImage -> pickImage()

            is AddCoffeeIntent.ImagePicked -> loadPickedImage(
                imageName = intent.imageName,
                imageByteArray = intent.imageByteArray
            )

            is AddCoffeeIntent.AddCoffeeBtnClicked -> onAddCoffeeBtnClicked(intent.coffeeInfo)
            // оператор "!!" используется потому что диалог не может быть показан, если coffeeInfo == null
            AddCoffeeIntent.ConfirmAlreadyExistDialog -> addCoffee(_state.value.coffeeInfo!!)
            AddCoffeeIntent.DismissAlreadyExistDialog -> updateState(AddCoffeeResults.CloseCoffeeAlreadyExistDialog)
        }
    }

    private fun loadCoffeeInfo(imageByteArray: ByteArray) {
        updateState(result = AddCoffeeResults.Loading)

        scope.launch {
            when (val result = addCoffeeInteractor.getCoffeeDetailsFromImage(imageByteArray)) {
                is AddCoffeeRepositoryResult.Error -> {
                    updateState(
                        result = AddCoffeeResults.CoffeeInfoError(result.errorMessage)
                    )
                }

                is AddCoffeeRepositoryResult.Success -> {
                    updateState(
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
            updateState(
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
                updateState(AddCoffeeResults.ShowCoffeeAlreadyExistDialog)
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
        updateState(AddCoffeeResults.CloseCoffeeAlreadyExistDialog)
        emitAction(AddCoffeeActions.AddCoffeeBtnClicked)
    }

    private fun pickImage() {
        emitAction(AddCoffeeActions.OpenGallery)
    }

    private fun emitAction(action: AddCoffeeActions) {
        scope.launch {
            _uiActions.emit(action)
        }
    }

    private fun updateState(result: AddCoffeeResults) {
        _state.update { oldState ->
            reducer.reduce(
                oldState = oldState,
                result = result
            )
        }
    }
}