package org.example.project.features.recipeDetails.data.repository

import kotlinx.coroutines.withContext
import org.example.project.core.domain.api.CoroutineDispatchers
import org.example.project.core.domain.api.ResourceManager
import org.example.project.features.recipeDetails.domain.api.LoaderScreenRepository
import kotlin.random.Random

class LoaderScreenRepositoryImpl(
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatchers,
) : LoaderScreenRepository {
    override suspend fun getRandomFact(): String {
        return withContext(dispatcher.io()) {
            val rawFacts = resourceManager.getFileResource("files/FactsAboutFilterCoffee.txt")
            val facts = rawFacts.split("\n")
            facts[Random.nextInt(0, facts.lastIndex)]
        }
    }
}