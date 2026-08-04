package org.example.project.features.addCoffee.domain

import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryResult

interface AddCoffeeRepository {

    suspend fun getCoffeeDetailsFromImage(imageBase64: String): AddCoffeeRepositoryResult

    suspend fun saveCoffee(coffee: Coffee)
}