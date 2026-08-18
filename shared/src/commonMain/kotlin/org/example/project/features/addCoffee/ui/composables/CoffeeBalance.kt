package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.white
import org.example.project.core.ui.components.CustomProgressIndicator
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.density_label
import coffee.shared.generated.resources.acidity_label
import coffee.shared.generated.resources.ic_coffee_bean_density_24
import coffee.shared.generated.resources.ic_coffee_berry_acidity_24
import org.example.project.core.ui.theme.accentColor
import org.example.project.core.ui.theme.getMontserratMedium
import org.example.project.core.ui.theme.lightOrange
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Preview
@Composable
fun CoffeeBalance(
    modifier: Modifier = Modifier,
    density: Float = 0.5f,
    acidity: Float = 0.5f,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_coffee_bean_density_24),
                contentDescription = null,
                tint = accentColor
            )
            Spacer(Modifier.width(5.dp))
            RegularAppText(
                text = stringResource(Res.string.density_label),
                fontSize = 16.sp,
                fontFamily = getMontserratMedium()
            )
            Spacer(Modifier.weight(1f))
            CustomProgressIndicator(
                modifier = Modifier
                    .width(200.dp)
                    .height(7.dp),
                progress = density,
                color = accentColor
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_coffee_berry_acidity_24),
                contentDescription = null,
                tint = lightOrange
            )
            Spacer(Modifier.width(5.dp))
            RegularAppText(
                text = stringResource(Res.string.acidity_label),
                fontSize = 16.sp,
                fontFamily = getMontserratMedium()
            )
            Spacer(Modifier.weight(1f))
            CustomProgressIndicator(
                modifier = Modifier
                    .width(200.dp)
                    .height(7.dp),
                progress = acidity,
                color = lightOrange
            )
        }
    }
}