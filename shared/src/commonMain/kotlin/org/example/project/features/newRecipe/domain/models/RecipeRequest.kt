package org.example.project.features.newRecipe.domain.models

import kotlinx.serialization.Serializable
import org.example.project.core.domain.model.Coffee

@Serializable
data class RecipeRequest(
    val coffee: Coffee,
    val waterAmount: Int
)
