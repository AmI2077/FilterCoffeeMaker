package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_coffee_beans_24
import coffee.shared.generated.resources.ic_cup_24
import coffee.shared.generated.resources.ic_temperature_24
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.textSecondaryColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun ComponentsRow(
    modifier: Modifier = Modifier,
    coffeeAmount: String,
    waterAmount: String,
    temperature: String,
) {
    Row(
        modifier = modifier.wrapContentHeight(), verticalAlignment = Alignment.CenterVertically
    ) {
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = coffeeAmount,
            painter = painterResource(Res.drawable.ic_coffee_beans_24)
        )
        VerticalDivider(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(80.dp),
            color = textSecondaryColor
        )
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = waterAmount,
            painter = painterResource(Res.drawable.ic_cup_24),
        )
        VerticalDivider(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(80.dp),
            color = textSecondaryColor
        )
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = temperature,
            painter = painterResource(Res.drawable.ic_temperature_24)
        )
    }
}


@Composable
fun ComponentItem(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegularAppText(
            text = text,
        )
        Icon(
            modifier = Modifier.size(30.dp), painter = painter, contentDescription = null
        )
    }
}