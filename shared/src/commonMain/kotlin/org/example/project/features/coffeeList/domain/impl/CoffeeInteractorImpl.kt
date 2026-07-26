package org.example.project.features.coffeeList.domain.impl

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee
import org.example.project.features.coffeeList.domain.api.CoffeeInteractor
import org.example.project.features.coffeeList.domain.api.CoffeeRepository

class CoffeeInteractorImpl(
    private val coffeeRepository: CoffeeRepository
) : CoffeeInteractor {

    override fun getCoffeeList(): Flow<List<Coffee>> {
        return coffeeRepository.getCoffeeList()
    }
}