package org.example.project.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.acidity_label
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.textSecondaryColor
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round
import kotlin.math.pow

@Preview
@Composable
fun AppSlider(
    modifier: Modifier = Modifier,
    value: Float = 0.6f,
    onValueChangeFinished: (Float) -> Unit = {},
) {
    val sliderState = rememberSliderState(
        value = value,
        onValueChangeFinished = {},
    )
    onValueChangeFinished(sliderState.value)
    Row(
        modifier = modifier.background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            modifier = Modifier.weight(0.8f),
            state = sliderState,
            colors = SliderDefaults.colors(
                thumbColor = black,
            ),
            thumb = {
                SliderThumb()
            },
            track = {
                SliderDefaults.Track(
                    modifier = Modifier.height(10.dp),
                    sliderState = sliderState,
                    colors = SliderDefaults.colors(
                        inactiveTrackColor = lightGray,
                        activeTrackColor = black
                    )
                )
            }
        )
        RegularAppText(
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center,
            text = "${roundFloatValue(sliderState.value, 2)}",
            fontFamily = getMontserratBold(),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun SliderThumb(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .height(20.dp)
            .width(5.dp)
            .background(
                color = black,
                shape = RoundedCornerShape(50)
            )
    )
}

fun roundFloatValue(value: Float, level: Int): Float {
    return round(value * 10f.pow(level)) / 10f.pow(level)
}