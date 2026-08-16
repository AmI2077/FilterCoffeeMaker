package org.example.project.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.white
import org.example.project.features.coffeeDetails.ui.composables.ButtonRow
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.dialog_no
import coffee.shared.generated.resources.dialog_yes
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    message: String,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {}
    ) {
        DialogContent(
            modifier = modifier,
            message = message,
            onDismissClick = onDismissClick,
            onConfirmClick = onConfirmClick
        )
    }
}

@Composable
private fun DialogContent(
    modifier: Modifier = Modifier,
    message: String,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
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
            text = message,
            color = black,
            fontSize = 20.sp,
            fontFamily = getMontserratBold(),
            maxLines = Int.MAX_VALUE
        )
        Spacer(Modifier.padding(top = 30.dp))
        ButtonRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            firstBtn = {
                DismissBtn(onClick = { onDismissClick() })
            },
            secondBtn = {
                AcceptBtn(onClick = { onConfirmClick() })
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
                text = stringResource(Res.string.dialog_no),
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
                text = stringResource(Res.string.dialog_yes),
                fontFamily = getMontserratBold(),
                color = white,
            )
        },
        icon = null,
        containerColor = black
    ) {
        onClick()
    }
}