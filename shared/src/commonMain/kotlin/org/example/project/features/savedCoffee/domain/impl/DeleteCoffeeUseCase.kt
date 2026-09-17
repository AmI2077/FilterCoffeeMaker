package org.example.project.features.savedCoffee.domain.impl

import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class DeleteCoffeeUseCase(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke(coffee: Coffee): Boolean {
        return coffeeRepository.deleteCoffee(coffee)
    }
}