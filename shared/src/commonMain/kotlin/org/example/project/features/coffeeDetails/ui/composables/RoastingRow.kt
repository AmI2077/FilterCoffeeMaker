package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.getMontserratRegular
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.roasting_with_comma
import org.example.project.core.ui.theme.accentColor
import org.example.project.core.ui.theme.textPrimaryColorLight
import org.jetbrains.compose.resources.stringResource

@Composable
fun RoastingAndProcessingRow(
    modifier: Modifier = Modifier,
    roasting: String,
    processingMethod: String,
) {
    Row(
        modifier = modifier
            .background(
                color = accentColor.copy(alpha = 0.5f),
                shape = CircleShape
            ).padding(10.dp),
    ) {
        RegularAppText(
            text = stringResource(Res.string.roasting_with_comma, roasting),
            fontSize = 14.sp,
            color = textPrimaryColorLight,
            fontFamily = getMontserratRegular()
        )
        RegularAppText(
            text = processingMethod,
            fontSize = 14.sp,
            color = textPrimaryColorLight,
            fontFamily = getMontserratRegular()
        )
    }
}