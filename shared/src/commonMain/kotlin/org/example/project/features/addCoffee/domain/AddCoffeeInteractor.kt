package org.example.project.features.addCoffee.domain

import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.data.repository.AddCoffeeRepositoryResult

interface AddCoffeeInteractor {
    suspend fun getCoffeeDetailsFromImage(imageByteArray: ByteArray): AddCoffeeRepositoryResult

    suspend fun saveCoffee(coffee: Coffee)
}