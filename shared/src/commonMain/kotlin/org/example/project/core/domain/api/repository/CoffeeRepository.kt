package org.example.project.core.domain.api.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee

interface CoffeeRepository {

    suspend fun getCoffeeDetails(coffeeId: String): Coffee?

    fun getCoffeeDetailsFlow(coffeeId: String): Flow<Coffee>

    fun getUserCoffeeList(): Flow<List<Coffee>>

    suspend fun addCoffee(coffee: Coffee): Boolean

    suspend fun updateCoffee(updatedCoffee: Coffee): Boolean

    suspend fun deleteCoffee(coffee: Coffee): Boolean
}
