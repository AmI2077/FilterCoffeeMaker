package org.example.project.features.timer.ui.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.model.BrewStep
import org.example.project.core.domain.model.Recipe
import org.example.project.features.timer.ui.state.TimerScreenUiState
import kotlin.time.Duration.Companion.seconds

class TimerScreenModel(
    private val recipe: Recipe
) : ScreenModel {

    private val initState = TimerScreenUiState(
        currentTime = 0,
        currentStep = recipe.brewSteps.first(),
        stepNumber = 0
    )

    private var _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    fun startTimer() {
        screenModelScope.launch {
            recipe.brewSteps.forEachIndexed { index, step ->
                println("CURRENT_STEP: $index - $step")
                println("TIMER_VALUE: ${_state.value.currentStep}")
                updateUiState(
                    currentStep = step,
                    stepNumber = index + 1
                )
                val endTime = step.endTime

                while (_state.value.currentTime < endTime) {
                    updateUiState(
                        currentTime = _state.value.currentTime + 1
                    )
                    delay(1.seconds)
                }
            }
        }
    }

    private fun updateUiState(
        currentTime: Int? = null,
        currentStep: BrewStep? = null,
        stepNumber: Int? = null
    ) {
        _state.update { currentState ->
            currentState.copy(
                currentTime = currentTime ?: currentState.currentTime,
                currentStep = currentStep ?: currentState.currentStep,
                stepNumber = stepNumber ?: currentState.stepNumber
            )
        }
    }
}

