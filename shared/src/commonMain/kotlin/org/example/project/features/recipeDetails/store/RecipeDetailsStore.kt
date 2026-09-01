package org.example.project.features.recipeDetails.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.core.domain.api.ImageSaver
import org.example.project.core.domain.impl.getWithImageDirectory
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.store.MviStore
import org.example.project.core.ui.store.emitAction
import org.example.project.core.ui.store.updateStateWithReducer
import org.example.project.features.coffeeDetails.data.CoffeeDetailsRepository
import org.example.project.features.recipeDetails.domain.api.RecipeDetailsRepository
import org.example.project.features.recipeDetails.domain.models.RecipeRequest
import org.example.project.features.recipeDetails.store.RecipeDetailsAction.*

// TODO "рефактор"

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

            is RecipeDetailsScreenIntent.StartTimerBtnClicked -> {
                val recipe = _state.value.content ?: throw IllegalStateException("recipe == null")

                saveRecipeToRecents(recipe, intent.coffeeId!!)
                _uiActions.emitAction(scope, OpenTimerScreen(recipe))
            }

            is RecipeDetailsScreenIntent.DefineInitState -> initState(intent.recipe)
            is RecipeDetailsScreenIntent.FavouriteBtnClicked -> saveRecipeToFavourites(_state.value.content!!, intent.coffeeId!!)
        }
    }

    // TODO "не трогай, не работает, надо разобраться с coffeeId, оно заебло меня"
    private fun saveRecipeToFavourites(recipe: Recipe, coffeeId: String) {
        scope.launch {
            recipeDetailsRepository.saveRecipesToFavourites(recipe)
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

            _state.updateStateWithReducer(
                reducer,
                RecipeDetailsResult.RecipeDetailsLoaded(
                    makeRecipeWithImage(coffee, recipe)
                )
            )
        }
    }

    private suspend fun makeRecipeWithImage(coffee: Coffee, recipe: Recipe): Recipe {
        val coffeeWithImage = coffee.getWithImageDirectory(imageSaver)

        return recipe.copy(
            coffee = coffeeWithImage
        )
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
        if (recipe == null) {
            _state.updateStateWithReducer(reducer, RecipeDetailsResult.ShowWaterAmountDialog)
        } else {
            _state.updateStateWithReducer(reducer, RecipeDetailsResult.RecipeDetailsLoaded(recipe))
        }
    }
}
