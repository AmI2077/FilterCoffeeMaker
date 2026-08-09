package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.getComfortaRegular

@Composable
fun RoastingAndProcessingRow(
    modifier: Modifier = Modifier,
    roasting: String,
    processingMethod: String,
) {
    Row(
        modifier = modifier,
    ) {
        RegularAppText(
            text = "${roasting}, ",
            fontSize = 20.sp,
            color = blueGrayText,
            fontFamily = getComfortaRegular()
        )
        RegularAppText(
            text = processingMethod,
            fontSize = 20.sp,
            color = blueGrayText,
            fontFamily = getComfortaRegular()
        )
    }
}