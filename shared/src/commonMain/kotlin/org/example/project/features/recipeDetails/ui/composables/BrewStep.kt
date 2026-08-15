package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_clock_24
import coffee.shared.generated.resources.ic_water_drip_24
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import org.example.project.core.utils.toTimeString
import org.jetbrains.compose.resources.painterResource

@Composable
fun BrewStep(
    modifier: Modifier = Modifier,
    number: Int,
    startTime: Int,
    endTime: Int,
    waterAmount: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = textSecondaryColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            RegularAppText(
                text = number.toString(),
                fontSize = 18.sp,
                fontFamily = getComfortaBold()
            )
        }

        Spacer(Modifier.width(20.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(Res.drawable.ic_clock_24),
            contentDescription = null,
        )
        Spacer(Modifier.width(5.dp))
        RegularAppText(
            modifier = Modifier.padding(start = 5.dp),
            text = "${startTime.toTimeString()} - ${endTime.toTimeString()}",
            color = textSecondaryColor,
            fontSize = 18.sp
        )
        Spacer(Modifier.weight(1f))
        RegularAppText(
            text = "$waterAmount мл",
            fontFamily = getComfortaBold(),
        )
        Spacer(Modifier.width(10.dp))
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(Res.drawable.ic_water_drip_24),
            contentDescription = null
        )
    }
}