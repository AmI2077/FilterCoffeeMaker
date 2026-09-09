package org.example.project.features.addCoffee.domain.useCases

import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee
import org.example.project.features.addCoffee.domain.AddCoffeeRepository

class SaveCoffeeUseCase(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke(coffee: Coffee): Boolean {
        return coffeeRepository.addCoffee(coffee)
    }
}