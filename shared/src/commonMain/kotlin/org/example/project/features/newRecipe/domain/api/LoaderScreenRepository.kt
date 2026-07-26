package org.example.project.features.newRecipe.domain.api

interface LoaderScreenRepository {

    suspend fun getRandomFact(): String
}