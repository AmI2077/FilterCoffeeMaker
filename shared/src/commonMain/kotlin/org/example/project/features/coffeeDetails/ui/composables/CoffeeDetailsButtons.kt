package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.recipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.regularTextStyle
import org.example.project.core.ui.theme.white
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
                style = regularTextStyle.copy(
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