package org.example.project.features.recentRecipes.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.data.extensions.getWithImageDirectory
import org.example.project.core.domain.api.ImageSaver
import org.example.project.features.recentRecipes.domain.api.RecipesRepository

class RecipesScreenModel(
    private val recipesRepository: RecipesRepository,
    private val imageSaver: ImageSaver,
) : ScreenModel {

    private var _state = MutableStateFlow(RecipesScreenUiState())
    val state = _state.asStateFlow()

    init {
        getRecentRecipes()
    }

    fun getRecentRecipes() {
        screenModelScope.launch {
            recipesRepository.getRecentRecipes()
                .map { recipes ->
                    recipes.map { recipe ->
                        val coffee = recipe.coffee.getWithImageDirectory(imageSaver)
                        recipe.copy(
                            coffee = coffee
                        )
                    }
                }
                .collect { recentRecipes ->
                    _state.update {
                        RecipesScreenUiState(
                            recentRecipes = recentRecipes
                        )
                    }
                }
        }
    }
}