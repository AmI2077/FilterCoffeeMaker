package org.example.project.features.newCoffee.domain

import org.example.project.core.domain.model.Coffee
import org.example.project.features.newCoffee.data.repository.AddCoffeeRepositoryResult

interface AddCoffeeInteractor {
    suspend fun getCoffeeDetailsFromImage(imageByteArray: ByteArray): AddCoffeeRepositoryResult

    suspend fun saveCoffee(coffee: Coffee)
}