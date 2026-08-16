package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.white
import org.example.project.core.ui.components.CustomProgressIndicator
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.density_label
import coffee.shared.generated.resources.acidity_label
import org.jetbrains.compose.resources.stringResource

@Preview
@Composable
fun CoffeeBalance(
    modifier: Modifier = Modifier,
    density: Float = 0.5f,
    acidity: Float = 0.5f,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(white)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegularAppText(
                text = stringResource(Res.string.density_label),
                fontSize = 16.sp,
            )
            Spacer(Modifier.width(60.dp))
            CustomProgressIndicator(
                modifier = Modifier
                    .height(7.dp),
                progress = density
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegularAppText(
                text = stringResource(Res.string.acidity_label),
                fontSize = 16.sp,
            )
            Spacer(Modifier.width(60.dp))
            CustomProgressIndicator(
                modifier = Modifier
                    .height(7.dp),
                progress = acidity
            )
        }
    }
}