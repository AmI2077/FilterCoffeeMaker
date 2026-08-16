package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ml_suffix
import coffee.shared.generated.resources.ok_button
import coffee.shared.generated.resources.water_amount_dialog_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun WaterAmountDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: (amount: Int) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            onDismissRequest(text.toInt())
        }
    ) {
        Column(
            modifier = modifier
                .background(
                    color = white,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp)
        ) {
            RegularAppText(
                text = stringResource(Res.string.water_amount_dialog_title),
                color = textSecondaryColor,
                fontSize = 18.sp
            )
            Spacer(Modifier.padding(top = 30.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                suffix = {
                    RegularAppText(
                        text = stringResource(Res.string.ml_suffix),
                        color = textSecondaryColor,
                        fontSize = 18.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lightGray,
                    unfocusedBorderColor = lightGray
                )
            )
            Spacer(Modifier.padding(top = 30.dp))
            AppButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = {
                    RegularAppText(
                        modifier = Modifier.padding(horizontal = 50.dp),
                        text = stringResource(Res.string.ok_button),
                        color = white
                    )
                },
                icon = null
            ) {
                onDismissRequest(text.toInt())
            }
        }
    }
}