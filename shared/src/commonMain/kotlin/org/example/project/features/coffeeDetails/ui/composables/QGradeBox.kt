package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.white

@Composable
fun QGradeBox(
    modifier: Modifier = Modifier,
    qGrade: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = white.copy(alpha = 0.8f),
            )
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        RegularAppText(
            text = "Q Grade",
            fontSize = 12.sp,
            color = black
        )
        RegularAppText(
            text = qGrade,
            fontSize = 26.sp,
            color = black,
            fontFamily = getComfortaBold()
        )
    }
}