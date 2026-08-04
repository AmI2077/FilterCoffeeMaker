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
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.white

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
                text = "Введи сколько хочешь кофе)",
                color = blueGrayText,
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
                        text = "мл",
                        color = blueGrayText,
                        fontSize = 18.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = blueGrayText,
                    unfocusedBorderColor = blueGrayText
                )
            )
            Spacer(Modifier.padding(top = 30.dp))
            AppButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = {
                    RegularAppText(
                        modifier = Modifier.padding(horizontal = 50.dp),
                        text = "Ок",
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