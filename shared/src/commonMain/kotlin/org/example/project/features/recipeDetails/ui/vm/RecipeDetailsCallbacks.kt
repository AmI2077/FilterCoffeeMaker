package org.example.project.features.recipeDetails.ui.vm

interface RecipeDetailsCallbacks {

    fun defineInitState()

    fun loadRecipeFromAi(waterAmount: Int)

    fun onFavBtnClick()

    fun onStartTimerClick()
}