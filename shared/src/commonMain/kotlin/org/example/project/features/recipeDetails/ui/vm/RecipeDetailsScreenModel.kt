package org.example.project.features.recipeDetails.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import org.example.project.core.domain.model.Recipe
import org.example.project.features.recipeDetails.store.RecipeDetailsScreenIntent
import org.example.project.features.recipeDetails.store.RecipeDetailsStore

class RecipeDetailsScreenModel(
    private val coffeeId: String?,
    private val recipe: Recipe? = null,
    storeFactory: (scope: CoroutineScope) -> RecipeDetailsStore
) : ScreenModel, RecipeDetailsCallbacks {
    private val store = storeFactory(screenModelScope)

    val state = store.state

    val uiActions = store.uiActions

    init {
        defineInitState()
    }

    override fun defineInitState() {
        store.onIntent(RecipeDetailsScreenIntent.DefineInitState(recipe))
    }

    override fun loadRecipeFromAi(waterAmount: Int) {
        store.onIntent(RecipeDetailsScreenIntent.LoadRecipeDetails(waterAmount, coffeeId))
    }
}
