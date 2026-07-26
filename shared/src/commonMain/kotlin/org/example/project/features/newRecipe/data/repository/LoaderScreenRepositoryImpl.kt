package org.example.project.features.newRecipe.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.example.project.core.data.resources.ResourceManager
import org.example.project.features.newRecipe.domain.api.LoaderScreenRepository
import kotlin.random.Random

class LoaderScreenRepositoryImpl(
    private val resourceManager: ResourceManager,
    private val dispatcher: CoroutineDispatcher,
) : LoaderScreenRepository {
    override suspend fun getRandomFact(): String {
        return withContext(dispatcher) {
            val rawFacts = resourceManager.getFileResource("files/FactsAboutFilterCoffee.txt")
            val facts = rawFacts.split("\n")
            facts[Random.nextInt(0, facts.lastIndex)]
        }
    }
}