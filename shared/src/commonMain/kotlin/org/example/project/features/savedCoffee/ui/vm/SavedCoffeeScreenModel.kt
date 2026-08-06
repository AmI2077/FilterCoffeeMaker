package org.example.project.features.savedCoffee.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.features.savedCoffee.domain.api.CoffeeInteractor
import org.example.project.features.savedCoffee.ui.states.CoffeeScreenUiState

class SavedCoffeeScreenModel(
    private val coffeeInteractor: CoffeeInteractor,
    private val imageSaver: ImageSaver,
) : ScreenModel {

    private var _state = MutableStateFlow(CoffeeScreenUiState())
    val state = _state.asStateFlow()

    init {
        getCoffeeList()
    }

    private fun getCoffeeList() {
        screenModelScope.launch {
            coffeeInteractor.getCoffeeList()
                .map { coffeeList ->
                    coffeeList.map { coffee ->
                        coffee.imagePath?.let {
                            coffee.copy(
                                imagePath = imageSaver.getDirectory(coffee.imagePath)
                            )
                        } ?: coffee
                    }
                }
                .collect { coffeeList ->
                    _state.update {
                        CoffeeScreenUiState(coffeeList)
                    }
                }
        }
    }

    override fun onDispose() {
        super.onDispose()
    }
}