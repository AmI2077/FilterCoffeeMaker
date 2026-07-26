package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.white

@Composable
fun BoxScope.QGradeBox(
    modifier: Modifier = Modifier,
    qGrade: String,
) {
    Box(
        modifier = modifier
            .align(Alignment.TopEnd)
            .shadow(
                elevation = 15.dp,
                shape = CircleShape
            )
            .background(
                color = white,
                shape = CircleShape,
            )
            .padding(
                horizontal = 10.dp,
                vertical = 15.dp
            ),
    ) {
        RegularAppText(
            text = qGrade,
            fontSize = 18.sp,
            color = black,
            fontFamily = getComfortaBold()
        )
    }
}