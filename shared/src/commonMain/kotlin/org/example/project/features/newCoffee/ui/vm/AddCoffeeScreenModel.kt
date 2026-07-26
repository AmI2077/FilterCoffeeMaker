package org.example.project.features.newCoffee.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.features.newCoffee.data.repository.AddCoffeeRepositoryResult
import org.example.project.features.newCoffee.domain.AddCoffeeInteractor
import org.example.project.features.newCoffee.ui.states.AddCoffeeScreenIntents
import org.example.project.features.newCoffee.ui.states.AddCoffeeScreenUiEvents
import org.example.project.features.newCoffee.ui.states.AddCoffeeScreenUiState
import org.example.project.features.newCoffee.ui.states.AddCoffeeScreenUiStatus

class AddCoffeeScreenModel(
    private val addCoffeeInteractor: AddCoffeeInteractor,
    private val imageSaver: ImageSaver
) : ScreenModel {
    private var _state = MutableStateFlow(AddCoffeeScreenUiState())
    val state = _state.asStateFlow()

    private var _uiEvents = MutableSharedFlow<AddCoffeeScreenUiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun onIntent(intent: AddCoffeeScreenIntents) {
        when (intent) {
            AddCoffeeScreenIntents.AddCoffee -> {
                addCoffee()
            }

            is AddCoffeeScreenIntents.ImagePicked -> {
                imagePicked(intent.result)
            }

            AddCoffeeScreenIntents.LoadImage -> {
                loadImage()
            }

            AddCoffeeScreenIntents.PickImage -> {
                pickImage()
            }
        }
    }

    private fun pickImage() {
        screenModelScope.launch {
            _uiEvents.emit(AddCoffeeScreenUiEvents.PickPhoto)
        }
    }

    fun loadImage() {
        updateUiState(
            newStatus = AddCoffeeScreenUiStatus.Loading
        )
        screenModelScope.launch {

            _state.value.imageByteArray?.let {
                when (val result = addCoffeeInteractor.getCoffeeDetailsFromImage(it)) {
                    is AddCoffeeRepositoryResult.Error -> {
                        updateUiState(
                            newStatus = AddCoffeeScreenUiStatus.Error(result.errorMessage)
                        )
                    }

                    is AddCoffeeRepositoryResult.Success -> {
                        updateUiState(
                            newStatus = AddCoffeeScreenUiStatus.Content(
                                result.coffee.copy(imagePath = _state.value.imageName)
                            )
                        )
                    }
                }
            }
        }
    }

    fun addCoffee() {
        screenModelScope.launch {
            if (_state.value.status is AddCoffeeScreenUiStatus.Content) {
                val coffee = (_state.value.status as AddCoffeeScreenUiStatus.Content).coffee
                addCoffeeInteractor.saveCoffee(coffee)
            } else {
                throw IllegalArgumentException("Coffee == null")
            }
            _uiEvents.emit(AddCoffeeScreenUiEvents.NavigateToCoffeeScreen)
        }
    }

    fun imagePicked(result: ImagePickerResult) {
        when (result) {
            is ImagePickerResult.Success -> {
                screenModelScope.launch(Dispatchers.IO) {
                    val imageByteArray = result.first?.loadBytes()
                    val imageName = result.first?.fileName
                    println("COFFEE_IMAGE: $imageName")

                    if (imageByteArray != null && imageName != null) {
                        imageSaver.saveImage(imageName, imageByteArray)
                        println("COFFEE_IMAGE_NAME: $imageName")
                        updateUiState(
                            imageName = imageName,
                            imageDirectory = imageSaver.getDirectory(imageName),
                            imageByteArray = imageByteArray,
                            newStatus = AddCoffeeScreenUiStatus.PhotoLoaded
                        )
                    } else {
                        throw Exception("Не удалось загрузить фотографию")
                    }
                }
            }

            else -> updateUiState(newStatus = AddCoffeeScreenUiStatus.Idle)
        }
    }

    private fun updateUiState(
        imageName: String? = null,
        imageDirectory: String? = null,
        imageByteArray: ByteArray? = null,
        newStatus: AddCoffeeScreenUiStatus
    ) {
        _state.update {
            it.copy(
                imageName = imageName ?: it.imageName,
                imageDirectory = imageDirectory ?: it.imageDirectory,
                imageByteArray = imageByteArray ?: it.imageByteArray,
                status = newStatus
            )
        }
    }
}