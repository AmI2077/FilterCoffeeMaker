package org.example.project.features.editCoffee.store

import org.example.project.core.domain.model.Coffee

data class EditCoffeeUiState(
    val coffee: Coffee? = null,
)