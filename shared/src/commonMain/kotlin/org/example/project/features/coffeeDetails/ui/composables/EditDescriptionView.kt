package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.AppOutlinedTextField
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.white
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.cancel_button
import coffee.shared.generated.resources.edit_description_label
import coffee.shared.generated.resources.save_button
import org.example.project.core.ui.components.ButtonRow
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.red
import org.jetbrains.compose.resources.stringResource

@Preview
@Composable
fun EditDescriptionView(
    modifier: Modifier = Modifier,
    onSaveBtnClick: (desc: String) -> Unit = {},
    onCancellationClick: () -> Unit = {},
) {
    var description by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AppOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            label = stringResource(Res.string.edit_description_label),
            onTextChange = { description = it },
            borderColor = lightGray
        )
        Spacer(Modifier.height(5.dp))
        SaveAndCancelButtons(
            modifier = Modifier
                .align(Alignment.End),
            onSaveBtnClick = {
                onSaveBtnClick(description)
            },
            onCancellationClick = onCancellationClick
        )
    }
}

@Composable
private fun SaveAndCancelButtons(
    modifier: Modifier = Modifier,
    onSaveBtnClick: () -> Unit = {},
    onCancellationClick: () -> Unit = {},
) {
    ButtonRow(
        modifier = modifier,
        firstBtn = {
            AppButton(
                text = {
                    RegularAppText(
                        text = stringResource(Res.string.cancel_button),
                        fontSize = 14.sp,
                        color = white
                    )
                },
                contentPadding =
                    PaddingValues(horizontal = 5.dp, vertical = 10.dp),
                containerColor = red,
                icon = null,
                onClick = onCancellationClick
            )
        },
        secondBtn = {
            AppButton(
                text = {
                    RegularAppText(
                        text = stringResource(Res.string.save_button),
                        fontSize = 14.sp,
                        color = white
                    )
                },
                contentPadding =
                    PaddingValues(horizontal = 5.dp, vertical = 10.dp),
                icon = null,
                onClick = onSaveBtnClick
            )
        }
    )
}