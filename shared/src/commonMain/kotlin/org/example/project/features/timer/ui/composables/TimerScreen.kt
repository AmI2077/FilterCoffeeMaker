package org.example.project.features.timer.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_play_btn_24
import coffee.shared.generated.resources.ic_playforward_24
import coffee.shared.generated.resources.timer_pour_more
import coffee.shared.generated.resources.timer_step_of
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.utils.toTimeString
import org.example.project.features.coffeeDetails.ui.composables.ButtonRow
import org.example.project.features.timer.ui.state.TimerScreenUiState
import org.example.project.features.timer.ui.vm.TimerScreenModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    screenModel: TimerScreenModel
) {
    val state by screenModel.state.collectAsStateWithLifecycle()

    TimerScreenContent(
        modifier = modifier,
        state = state,
        recipe = recipe,
        onPlayBtnClick = { screenModel.startTimer() }
    )
}

@Composable
fun TimerScreenContent(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    state: TimerScreenUiState,
    onPlayBtnClick: () -> Unit,
) {
    val brewSteps = recipe.brewSteps

    val stepNumber = state.stepNumber
    val currentStep = state.currentStep
    val currentTime = state.currentTime

    val timeString = currentTime.toTimeString().replace(":", "\n")
    val textHint = currentStep.textHint

    var waterStepsAmount = 0
    brewSteps.forEachIndexed { index, step ->
        waterStepsAmount += if (index + 1 > stepNumber) {
            0
        } else {
            step.amountWater
        }
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.3f))
        Box(
            modifier = Modifier
                .background(
                    color = lightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        ) {
            RegularAppText(
                text = stringResource(Res.string.timer_step_of, stepNumber, brewSteps.size),
            )
        }
        Spacer(Modifier.padding(5.dp))
        RegularAppText(
            text = timeString,
            maxLines = 2,
            textAlign = TextAlign.Center,
            fontSize = 90.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.padding(top = 10.dp))
        if (waterStepsAmount > 0) {
            RegularAppText(
                fontSize = 25.sp,
                text = stringResource(Res.string.timer_pour_more, currentStep.amountWater, waterStepsAmount),
            )
        }
        Spacer(Modifier.padding(top = 20.dp))
        textHint?.let {
            RegularAppText(
                color = textSecondaryColor,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Clip,
                maxLines = Int.MAX_VALUE,
                text = it,
            )
        }
        Spacer(Modifier.padding(top = 30.dp))
        BrewProgressRow(
            currentTime = currentTime,
            steps = brewSteps
        )
        Spacer(Modifier.weight(0.5f))

        ButtonRow(
            modifier = Modifier
                .fillMaxWidth(),
            firstBtn = {
                AppButton(
                    modifier = Modifier.weight(0.5f),
                    text = null,
                    icon = {
                        Icon(
                            modifier = Modifier
                                .padding(20.dp)
                                .size(32.dp),
                            painter = painterResource(Res.drawable.ic_play_btn_24),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        onPlayBtnClick()
                    }
                )
            },
            secondBtn = {
                AppButton(
                    containerColor = textSecondaryColor,
                    text = null,
                    icon = {
                        Icon(
                            modifier = Modifier
                                .padding(20.dp)
                                .size(32.dp),
                            painter = painterResource(Res.drawable.ic_playforward_24),
                            contentDescription = null
                        )
                    },
                    onClick = {}
                )
            }
        )
    }
}

