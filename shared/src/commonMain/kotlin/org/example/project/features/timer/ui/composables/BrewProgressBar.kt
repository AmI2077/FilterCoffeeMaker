package org.example.project.features.timer.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.domain.model.BrewStep
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.textSecondaryColor
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.brew_step_water
import org.jetbrains.compose.resources.stringResource

@Composable
fun BrewProgressRow(
    modifier: Modifier = Modifier,
    steps: List<BrewStep>,
    currentTime: Int
) {

    Row(modifier = modifier) {
        steps.forEach { step ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val stepProgress = calculateStepProgress(
                    currentTime = currentTime,
                    startTime = step.startTime,
                    endTime = step.endTime
                )

                StepProgressBar(
                    progress = stepProgress
                )
                Spacer(Modifier.padding(top = 5.dp))
                RegularAppText(
                    fontSize = 16.sp,
                    color = if (stepProgress == 1f) black else textSecondaryColor,
                    text = stringResource(Res.string.brew_step_water, step.amountWater)
                )
            }
        }
    }
}

private fun calculateStepProgress(
    currentTime: Int,
    startTime: Int,
    endTime: Int,
): Float {
    if (currentTime < startTime) {
        return 0f
    }
    if (currentTime > endTime) {
        return 1f
    }
    return (currentTime - startTime).toFloat() / endTime.toFloat()
}