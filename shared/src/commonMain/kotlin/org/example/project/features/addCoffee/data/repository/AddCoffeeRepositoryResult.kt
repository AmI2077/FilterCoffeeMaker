package org.example.project.features.addCoffee.data.repository

import org.example.project.core.domain.model.Coffee

sealed interface AddCoffeeRepositoryResult {
    data class Success(val coffee: Coffee) : AddCoffeeRepositoryResult
    data class Error(val errorMessage: String) : AddCoffeeRepositoryResult
}