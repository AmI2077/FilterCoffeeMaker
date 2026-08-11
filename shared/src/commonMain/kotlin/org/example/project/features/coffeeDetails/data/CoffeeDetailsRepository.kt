package org.example.project.features.coffeeDetails.data

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee

interface CoffeeDetailsRepository {

    suspend fun getCoffeeDetailsFlow(coffeeId: String): Flow<Coffee>

    suspend fun getCoffeeDetails(coffeeId: String): Coffee?

    suspend fun editCoffee(coffee: Coffee)
}