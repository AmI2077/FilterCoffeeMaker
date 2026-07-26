package org.example.project.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BrewStep(
    val startTime: Int,
    val endTime: Int,
    val amountWater: Int,
    val textHint: String? = null,
)
