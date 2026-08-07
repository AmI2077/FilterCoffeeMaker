package org.example.project.features.savedCoffee.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.red
import org.example.project.core.ui.theme.white
import org.example.project.features.coffeeDetails.ui.composables.ButtonRow

sealed interface DialogResult {
    data class Confirm(val coffee: Coffee): DialogResult
    data object Dismiss: DialogResult
}

@Composable
fun DeleteCoffeeDialog(
    modifier: Modifier = Modifier,
    onConfirmRequest: () -> Unit,
    coffeeTitle: String,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = {

        }
    ) {
        DeleteCoffeeDialogContent(
            modifier = modifier,
            onConfirmRequest = onConfirmRequest,
            coffeeTitle = coffeeTitle,
            onDismissRequest = onDismissRequest
        )
    }
}

@Preview
@Composable
private fun DeleteCoffeeDialogContent(
    modifier: Modifier = Modifier,
    onConfirmRequest: () -> Unit = {},
    coffeeTitle: String = "Гватемала Сантьяго",
    onDismissRequest: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(300.dp)
            .background(
                color = white,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(25.dp)
    ) {
        RegularAppText(
            text = "Удалить $coffeeTitle?",
            color = black,
            fontSize = 20.sp,
            fontFamily = getComfortaBold(),
            maxLines = 2
        )
        Spacer(Modifier.padding(top = 30.dp))
        ButtonRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            firstBtn = {
                DismissBtn(onClick = { onDismissRequest() })
            },
            secondBtn = {
                AcceptBtn(onClick = { onConfirmRequest() })
            }
        )
    }
}

@Composable
private fun DismissBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AppButton(
        modifier = modifier
            .width(100.dp),
        text = {
            RegularAppText(
                modifier = Modifier.padding(10.dp),
                text = "Нет",
                color = white,
            )
        },
        icon = null
    ) {
        onClick()
    }
}

@Composable
private fun AcceptBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AppButton(
        modifier = modifier
            .width(100.dp),
        text = {
            RegularAppText(
                modifier = Modifier.padding(10.dp),
                text = "Да",
                fontFamily = getComfortaBold(),
                color = white,
            )
        },
        icon = null,
        containerColor = red
    ) {
        onClick()
    }
}