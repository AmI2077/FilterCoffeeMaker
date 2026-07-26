package org.example.project.features.timer.ui.state

import org.example.project.core.domain.model.BrewStep

data class TimerScreenUiState(
    val currentTime: Int,
    val currentStep: BrewStep,
    val stepNumber: Int,
)