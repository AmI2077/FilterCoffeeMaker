package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.description_label
import org.jetbrains.compose.resources.stringResource

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
                width = 1.dp,
                color = textSecondaryColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Column {
            RegularAppText(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(Res.string.description_label),
                fontSize = 16.sp,
                maxLines = Int.MAX_VALUE
            )
            Spacer(Modifier.height(15.dp))
            RegularAppText(
                text = description,
                fontSize = 16.sp,
                maxLines = Int.MAX_VALUE,
                color = textSecondaryColor
            )
        }

    }
}