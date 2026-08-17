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
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.white
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.q_grade_label
import coffee.shared.generated.resources.q_grade_value
import org.example.project.core.ui.theme.UiDefaults
import org.jetbrains.compose.resources.stringResource

@Composable
fun QGradeBox(
    modifier: Modifier = Modifier,
    qGrade: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = white.copy(alpha = 0.6f),
            )
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        RegularAppText(
            text = stringResource(Res.string.q_grade_label),
            fontSize = UiDefaults.CARD_SMALL_TEXT_SIZE.sp,
            color = black
        )
        RegularAppText(
            text = stringResource(Res.string.q_grade_value, qGrade),
            fontSize = UiDefaults.CARD_HEADER_TEXT_SIZE.sp,
            color = black,
            fontFamily = getMontserratBold()
        )
    }
}