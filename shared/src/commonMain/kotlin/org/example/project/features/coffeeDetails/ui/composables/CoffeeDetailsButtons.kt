package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_edit_24
import coffee.shared.generated.resources.recipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.regularTextStyle
import org.example.project.core.ui.theme.white
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoffeeDetailsButtons(
    modifier: Modifier = Modifier,
    onRecipeBtnClick: () -> Unit,
    onEditBtnClick: () -> Unit,
) {
    ButtonRow(
        modifier = modifier,
        firstBtn = {
            AppButton(
                modifier = Modifier.weight(0.8f),
                text = {
                    Text(
                        modifier = Modifier.padding(vertical = 15.dp),
                        text = stringResource(Res.string.recipe),
                        style = regularTextStyle.copy(
                            fontFamily = getComfortaRegular(),
                            color = white,
                            fontSize = 18.sp,
                        )
                    )
                },
                icon = null
            ) {
                onRecipeBtnClick()
            }
        },
        secondBtn = {
            AppButton(
                contentPadding = PaddingValues(vertical = 15.dp),
                text = null,
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(Res.drawable.ic_edit_24),
                        contentDescription = null,
                    )
                }
            ) {
                onEditBtnClick()
            }
        },
    )
}