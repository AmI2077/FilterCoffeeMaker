package org.example.project.features.recipeDetails.domain.api

interface LoaderScreenRepository {

    suspend fun getRandomFact(): String
}