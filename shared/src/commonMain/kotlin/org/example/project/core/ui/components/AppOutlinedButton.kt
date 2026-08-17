package org.example.project.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.ui.theme.black

@Composable
fun AppOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    borderStroke: BorderStroke = BorderStroke(2.dp, black),
    shape: Shape = CircleShape,
    contentPadding: PaddingValues = PaddingValues(15.dp),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        border = borderStroke,
        contentPadding = contentPadding,
        shape = shape,
        onClick = onClick
    ) {
        RegularAppText(
            text = text,
            color = black,
            fontSize = 18.sp
        )
    }
}