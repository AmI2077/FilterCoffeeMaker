package org.example.project.features.addCoffee.domain

import org.example.project.core.domain.model.NetworkResult
import org.example.project.core.domain.model.Coffee

interface AddCoffeeRepository <T> {

    suspend fun getCoffeeDetailsFromImage(imageBase64: String): NetworkResult<T>

    suspend fun isCoffeeExist(coffee: Coffee): Boolean

    suspend fun saveCoffee(coffee: Coffee)
}