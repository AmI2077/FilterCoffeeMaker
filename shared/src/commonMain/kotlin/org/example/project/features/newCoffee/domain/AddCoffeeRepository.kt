package org.example.project.features.newCoffee.domain

import org.example.project.core.domain.model.Coffee
import org.example.project.features.newCoffee.data.repository.AddCoffeeRepositoryResult

interface AddCoffeeRepository {

    suspend fun getCoffeeDetailsFromImage(imageBase64: String): AddCoffeeRepositoryResult

    suspend fun saveCoffee(coffee: Coffee)
}