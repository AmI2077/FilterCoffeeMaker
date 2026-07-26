package org.example.project.features.newRecipe.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.features.newRecipe.domain.api.LoaderScreenRepository
import kotlin.time.Duration.Companion.seconds

class RecipeLoaderScreenModel(
    private val loaderScreenRepository: LoaderScreenRepository
) : ScreenModel {

    private var _currentFact = MutableStateFlow("")
    val currentFact = _currentFact.asStateFlow()

    init {
        getFact()
    }

    private fun getFact() {
        screenModelScope.launch {
            while (true) {
                val fact = loaderScreenRepository.getRandomFact()
                _currentFact.update { fact }
                delay(3.seconds)
            }
        }
    }
}