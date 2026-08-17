package org.example.project.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.getMontserratBold
import kotlin.math.round
import kotlin.math.pow

@Composable
fun AppSlider(
    modifier: Modifier = Modifier,
    value: Float = 0.6f,
    onValueChangeFinished: (Float) -> Unit,
) {
    var sliderValue by remember { mutableStateOf(value) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            modifier = modifier
                .weight(0.8f),
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
            },
            onValueChangeFinished = {
                onValueChangeFinished(sliderValue)
            },
            colors = SliderDefaults.colors(
                thumbColor = black,
                activeTrackColor = black,
                inactiveTrackColor = lightGray
            ),
            thumb = {
                SliderThumb()
            },
        )
        RegularAppText(
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center,
            text = "${roundFloatValue(sliderValue, 2)}",
            fontFamily = getMontserratBold(),
            fontSize = 24.sp
        )
    }

}

@Composable
private fun SliderThumb(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .height(30.dp)
            .width(10.dp)
            .background(
                color = black,
                shape = RoundedCornerShape(50)
            )
    )
}

fun roundFloatValue(value: Float, level: Int): Float {
    return round(value * 10f.pow(level)) / 10f.pow(level)
}