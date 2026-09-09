package org.example.project.features.recentRecipes.domain.useCases

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.api.repository.RecipesRepository
import org.example.project.core.domain.model.Recipe

class GetRecentRecipesUseCase(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return recipesRepository.getRecentRecipes()
    }
}