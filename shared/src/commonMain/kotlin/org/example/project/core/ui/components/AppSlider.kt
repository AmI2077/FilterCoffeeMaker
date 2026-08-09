package org.example.project.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.getComfortaBold

@Preview
@Composable
fun AppSlider(
    modifier: Modifier = Modifier,
    value: Float = 0.6f
) {
    val sliderState = rememberSliderState(
        value = value
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            modifier = modifier
                .weight(0.8f),
            state = sliderState,
            colors = SliderDefaults.colors(
                thumbColor = black,
                activeTrackColor = black,
                inactiveTrackColor = blueGrayText
            ),
            thumb = {
                SliderThumb()
            }
        )
        RegularAppText(
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center,
            text = "${sliderState.value}",
            fontFamily = getComfortaBold(),
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