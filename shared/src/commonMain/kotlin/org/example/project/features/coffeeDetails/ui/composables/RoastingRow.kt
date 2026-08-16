package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.getComfortaRegular
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.roasting_with_comma
import org.jetbrains.compose.resources.stringResource

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
            text = stringResource(Res.string.roasting_with_comma, roasting),
            fontSize = 14.sp,
            color = textSecondaryColor,
            fontFamily = getComfortaRegular()
        )
        RegularAppText(
            text = processingMethod,
            fontSize = 14.sp,
            color = textSecondaryColor,
            fontFamily = getComfortaRegular()
        )
    }
}