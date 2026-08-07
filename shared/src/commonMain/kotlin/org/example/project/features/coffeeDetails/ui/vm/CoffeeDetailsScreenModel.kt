package org.example.project.features.coffeeDetails.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository

class CoffeeDetailsScreenModel(
    private val repository: CoffeeDetailsRepository,
    private val imageSaver: ImageSaver
) : ScreenModel {

    private var _state =
        MutableStateFlow<CoffeeDetailsScreenUiState>(CoffeeDetailsScreenUiState.Loading)
    val state = _state.asStateFlow()

    fun getCoffeeDetails(coffeeId: String) {
        _state.update {
            CoffeeDetailsScreenUiState.Loading
        }
        screenModelScope.launch {
            val coffee = repository.getCoffeeDetails(coffeeId)
            val directory = coffee.imagePath?.let {
                imageSaver.getDirectory(it)
            }
            _state.update {
                CoffeeDetailsScreenUiState.Content(
                    coffee = coffee.copy(
                        imagePath = directory
                    )
                )
            }
        }
    }
}