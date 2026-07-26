package org.example.project.features.newCoffee.domain

import org.example.project.core.domain.model.Coffee
import org.example.project.features.newCoffee.data.repository.AddCoffeeRepositoryResult
import kotlin.io.encoding.Base64

class AddCoffeeInteractorImpl(
    private val repository: AddCoffeeRepository
) : AddCoffeeInteractor {
    override suspend fun getCoffeeDetailsFromImage(imageByteArray: ByteArray): AddCoffeeRepositoryResult {
        return repository.getCoffeeDetailsFromImage(
            imageBase64 = Base64.encode(imageByteArray)
        )
    }

    override suspend fun saveCoffee(coffee: Coffee) {
        repository.saveCoffee(coffee)
    }
}