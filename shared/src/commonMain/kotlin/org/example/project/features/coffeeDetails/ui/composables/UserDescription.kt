package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.white

@Composable
fun UserDescription(
    modifier: Modifier = Modifier,
    description: String,
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS),
                color = white
            )
            .border(
                width = 2.dp,
                color = blueGrayText,
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS)
            )
            .padding(10.dp)
    ) {
        RegularAppText(
            text = description,
            fontSize = 18.sp,
            maxLines = Int.MAX_VALUE
        )
    }
}