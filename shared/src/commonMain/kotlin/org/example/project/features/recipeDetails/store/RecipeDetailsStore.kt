package org.example.project.features.recipeDetails.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.core.data.extensions.getWithImageDirectory
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.updateStateWithReducer
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

class RecipeDetailsStore(
    private val imageSaver: ImageSaver,
    private val recipeDetailsRepository: RecipeDetailsRepository,
    private val coffeeDetailsRepository: CoffeeDetailsRepository,
    private val reducer: RecipeDetailsReducer,
    private val scope: CoroutineScope
) : MviStore<RecipeDetailsScreenUiState, RecipeDetailsScreenIntent, RecipeDetailsAction> {

    private var _state = MutableStateFlow(RecipeDetailsScreenUiState())
    override val state = _state.asStateFlow()

    private var _uiActions = MutableSharedFlow<RecipeDetailsAction>()
    override val uiActions: SharedFlow<RecipeDetailsAction>
        get() = _uiActions

    override fun onIntent(intent: RecipeDetailsScreenIntent) {
        when (intent) {
            is RecipeDetailsScreenIntent.LoadRecipeDetails ->
                loadRecipeFromAi(
                    waterAmount = intent.waterAmount,
                    coffeeId = intent.coffeeId
                )

            is RecipeDetailsScreenIntent.SaveRecipeDetailsToRecents ->
                saveRecipeToRecents(intent.recipe, intent.coffeeId!!)

            is RecipeDetailsScreenIntent.DefineInitState -> initState(intent.recipe)
        }
    }

    private fun saveRecipeToRecents(recipe: Recipe, coffeeId: String) {
        scope.launch {
            recipeDetailsRepository.saveRecipeToRecents(recipe, coffeeId)
        }
    }

    private fun loadRecipeFromAi(waterAmount: Int, coffeeId: String?) {
        _state.updateStateWithReducer(reducer, RecipeDetailsResult.Loading)
        scope.launch {
            val coffee = getCoffeeDetails(coffeeId!!)
            val recipe = makeRecipeRequest(coffee, waterAmount)

            recipeDetailsRepository.saveRecipeToRecents(
                makeRecipeRequest(coffee, waterAmount),
                coffeeId
            )

            val recipeWithImage = recipe.copy(
                coffee = recipe.coffee.getWithImageDirectory(imageSaver)
            )
            _state.updateStateWithReducer(
                reducer,
                RecipeDetailsResult.RecipeDetailsLoaded(recipeWithImage)
            )
        }
    }

    private suspend fun makeRecipeRequest(coffee: Coffee, waterAmount: Int): Recipe {
        return recipeDetailsRepository.getRecipe(
            RecipeRequest(
                coffee = coffee,
                waterAmount = waterAmount,
            )
        )
    }

    private suspend fun getCoffeeDetails(coffeeId: String): Coffee {
        return checkNotNull(coffeeDetailsRepository.getCoffeeDetails(coffeeId)) {
            throw IllegalStateException("Coffee for recipe don't exist")
        }
    }

    private fun initState(recipe: Recipe?) {
        recipe?.let { recipe ->
            _state.updateStateWithReducer(reducer, RecipeDetailsResult.RecipeDetailsLoaded(recipe))
        } ?: {
            _state.updateStateWithReducer(reducer, RecipeDetailsResult.ShowWaterAmountDialog)
        }
    }
}
