package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_edit_24
import coffee.shared.generated.resources.recipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.theme.accentColor
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun RecipeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AppButton(
        modifier = modifier,
        text = {
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = stringResource(Res.string.recipe),
                style = TextStyle(
                    fontFamily = getMontserratBold(),
                    color = white,
                    fontSize = 20.sp,
                )
            )
        },
        icon = null
    ) {
        onClick()
    }
}

@Composable
fun EditButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = textSecondaryColor.copy(alpha = 0.6f),
                shape = RoundedCornerShape(20.dp)
            )
            .size(60.dp)
            .clickable(
                onClick = onClick
            )
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp),
            painter = painterResource(Res.drawable.ic_edit_24),
            tint = accentColor,
            contentDescription = null,
        )
    }
}