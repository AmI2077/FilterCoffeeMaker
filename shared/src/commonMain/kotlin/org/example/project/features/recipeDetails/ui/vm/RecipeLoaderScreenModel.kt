package org.example.project.features.recipeDetails.ui.vm

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.data.impl.BaseScreenModel
import org.example.project.features.recipeDetails.domain.api.LoaderScreenRepository
import kotlin.time.Duration.Companion.seconds

class RecipeLoaderScreenModel(
    private val loaderScreenRepository: LoaderScreenRepository
) : BaseScreenModel() {

    private var _currentFact = MutableStateFlow("")
    val currentFact = _currentFact.asStateFlow()

    init {
        getFact()
    }

    private fun getFact() {
        screenModelScopeWithHandler.launch {
            while (true) {
                val fact = loaderScreenRepository.getRandomFact()
                _currentFact.update { fact }
                delay(3.seconds)
            }
        }
    }
}