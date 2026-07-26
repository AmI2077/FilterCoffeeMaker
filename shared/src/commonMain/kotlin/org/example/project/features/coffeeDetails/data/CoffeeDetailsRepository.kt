package org.example.project.features.coffeeDetails.data

import org.example.project.core.domain.model.Coffee

interface CoffeeDetailsRepository {

    suspend fun getCoffeeDetails(coffeeId: Int): Coffee
}