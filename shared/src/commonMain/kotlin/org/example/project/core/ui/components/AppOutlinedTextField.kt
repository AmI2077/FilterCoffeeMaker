package org.example.project.core.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.regularTextStyle
import org.example.project.core.ui.theme.white

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    borderColor: Color = black,
    labelColor: Color = black,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = regularTextStyle.copy(
            fontFamily = getComfortaRegular()
        ),
        onValueChange = onTextChange,
        label = {
            RegularAppText(
                text = label,
                fontSize = 14.sp
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedContainerColor = white,
            unfocusedContainerColor = white
        ),
        shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS),
        readOnly = readOnly,
        trailingIcon = trailingIcon,
    )
}