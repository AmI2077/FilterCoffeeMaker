package org.example.project.features.savedCoffee.domain.api

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.model.Coffee

interface CoffeeInteractor {

    fun getCoffeeList(): Flow<List<Coffee>>
}