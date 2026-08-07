package org.example.project.features.savedCoffee.domain.api

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee

interface CoffeeRepository {

    fun getCoffeeList(): Flow<List<Coffee>>

    suspend fun deleteCoffee(coffee: Coffee)
}