package org.example.project.features.recentRecipes.ui.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.data.impl.BaseScreenModel
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.impl.getWithImageDirectory
import org.example.project.features.recentRecipes.domain.useCases.GetRecentRecipesUseCase

class RecipesScreenModel(
    private val getRecentRecipesUseCase: GetRecentRecipesUseCase,
    private val imageSaver: ImageSaver,
) : BaseScreenModel() {

    private var _state = MutableStateFlow(RecipesScreenUiState())
    val state = _state.asStateFlow()

    init {
        getRecentRecipes()
    }

    fun getRecentRecipes() {
        screenModelScopeWithHandler.launch {
            getRecentRecipesUseCase()
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