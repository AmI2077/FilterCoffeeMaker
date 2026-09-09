package org.example.project.features.coffeeDetails.domain

import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class EditCoffeeUseCase(
    private val repository: CoffeeRepository
) {
    suspend operator fun invoke(coffee: Coffee): Boolean {
        return repository.updateCoffee(coffee)
    }
}