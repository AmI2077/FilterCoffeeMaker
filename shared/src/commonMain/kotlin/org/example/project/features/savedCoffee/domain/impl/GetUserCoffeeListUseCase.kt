package org.example.project.features.savedCoffee.domain.impl

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.api.repository.CoffeeRepository
import org.example.project.core.domain.model.Coffee

class GetUserCoffeeListUseCase(
    private val coffeeRepository: CoffeeRepository,
) {
    operator fun invoke(): Flow<List<Coffee>> {
        return coffeeRepository.getUserCoffeeList()
    }
}