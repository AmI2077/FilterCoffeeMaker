package org.example.project.features.coffeeDetails.domain

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class GetCoffeeDetailsFlowUseCase(
    private val repository: CoffeeRepository,
) {

    operator fun invoke(coffeeId: String): Flow<Coffee> {
        return repository.getCoffeeDetailsFlow(coffeeId)
    }
}