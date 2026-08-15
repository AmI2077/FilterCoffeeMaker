package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_coffee_beans_24
import coffee.shared.generated.resources.ic_cup_24
import coffee.shared.generated.resources.ic_temperature_24
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import org.jetbrains.compose.resources.painterResource

@Composable
fun ComponentsRow(
    modifier: Modifier = Modifier,
    coffeeAmount: String,
    waterAmount: String,
    temperature: String,
) {
    Row(
        modifier = modifier
            .background(
                color = white,
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = coffeeAmount,
            painter = painterResource(Res.drawable.ic_coffee_beans_24),
            type = "Кофе"
        )
        VerticalDivider(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(60.dp),
            color = textSecondaryColor.copy(alpha = 0.4f)
        )
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = waterAmount,
            painter = painterResource(Res.drawable.ic_cup_24),
            type = "Вода"
        )
        VerticalDivider(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .height(60.dp),
            color = textSecondaryColor.copy(alpha = 0.4f)
        )
        ComponentItem(
            modifier = Modifier.weight(1f),
            text = temperature,
            painter = painterResource(Res.drawable.ic_temperature_24),
            type = "Температура"
        )
    }
}


@Composable
fun ComponentItem(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
    type: String,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {

    RegularAppText(
        text = text,
        fontSize = 18.sp,
        fontFamily = getComfortaBold()
    )
    Spacer(Modifier.height(10.dp))
    Icon(
        modifier = Modifier
            .size(24.dp),
        painter = painter,
        contentDescription = null
    )
    Spacer(Modifier.height(5.dp))
    RegularAppText(
        text = type,
        fontSize = 14.sp,
        fontFamily = getComfortaRegular(),
        color = textSecondaryColor
    )

}