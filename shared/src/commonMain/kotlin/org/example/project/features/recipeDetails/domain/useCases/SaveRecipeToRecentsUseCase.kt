package org.example.project.features.recipeDetails.domain.useCases

import org.example.project.core.domain.api.repository.RecipesRepository
import org.example.project.core.domain.model.Recipe

class SaveRecipeToRecentsUseCase(
    private val recipesRepository: RecipesRepository,
) {
    suspend operator fun invoke(recipe: Recipe, coffeeId: String) {
        recipesRepository.addRecipeToRecents(recipe, coffeeId)
    }
}