package org.example.project.features.addCoffee.data.repository

import org.example.project.core.domain.model.Coffee

sealed interface AddCoffeeRepositoryResult {
    data class Success(val coffee: Coffee) : AddCoffeeRepositoryResult
    data class Error(val errorMessage: String) : AddCoffeeRepositoryResult
}

object AddCoffeeRepositoryErrors {
    const val SERIALIZE_ERROR = "Нейронка не смогла, попробуй еще раз"
    const val SAVE_TO_DB_ERROR = "Такой кофе у тебя уже есть. Заменить?"
}