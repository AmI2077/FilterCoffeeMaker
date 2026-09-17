package org.example.project.features.coffeeDetails.domain

import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class GetCoffeeDetailsUseCase(
    private val repository: CoffeeRepository,
) {

    suspend operator fun invoke(coffeeId: String): Coffee? {
        return repository.getCoffeeDetails(coffeeId)
    }
}