package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
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
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.red
import org.example.project.core.ui.theme.white

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
            label = "Введите описание",
            onTextChange = { description = it }
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
                        text = "Отмена",
                        fontSize = 16.sp,
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
                        text = "Сохранить",
                        fontSize = 16.sp,
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