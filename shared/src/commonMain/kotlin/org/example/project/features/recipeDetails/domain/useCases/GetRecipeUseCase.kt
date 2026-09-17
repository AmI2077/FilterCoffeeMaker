package org.example.project.features.recipeDetails.domain.useCases

import org.example.project.core.domain.api.repository.AiClientRepository
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.Result
import org.example.project.features.recipeDetails.domain.models.RecipeRequest

class GetRecipeUseCase(
    private val aiClientRepository: AiClientRepository
) {
    suspend operator fun invoke(recipeRequest: RecipeRequest): Result<Recipe> {
        return aiClientRepository.getRecipeFromAi(recipeRequest)
    }
}