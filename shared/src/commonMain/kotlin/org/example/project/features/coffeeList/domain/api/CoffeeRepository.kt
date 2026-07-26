package org.example.project.features.coffeeList.domain.api

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee

interface CoffeeRepository {

    fun getCoffeeList(): Flow<List<Coffee>>
}