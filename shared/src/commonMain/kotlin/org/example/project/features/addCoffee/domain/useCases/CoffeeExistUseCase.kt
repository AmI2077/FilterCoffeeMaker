package org.example.project.features.addCoffee.domain.useCases

import org.example.project.core.domain.api.repository.CoffeeRepository

class CoffeeExistUseCase(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke(coffeeId: String): Boolean {
        return coffeeRepository.getCoffeeDetails(coffeeId) != null
    }
}