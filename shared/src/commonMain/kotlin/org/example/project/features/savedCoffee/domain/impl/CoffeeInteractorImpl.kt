package org.example.project.features.savedCoffee.domain.impl

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee
import org.example.project.features.savedCoffee.domain.api.CoffeeInteractor
import org.example.project.features.savedCoffee.domain.api.CoffeeRepository

class CoffeeInteractorImpl(
    private val coffeeRepository: CoffeeRepository
) : CoffeeInteractor {

    override fun getCoffeeList(): Flow<List<Coffee>> {
        return coffeeRepository.getCoffeeList()
    }

    override suspend fun deleteCoffee(coffee: Coffee) {
        coffeeRepository.deleteCoffee(coffee)
    }
}