package org.example.project.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.white

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    shape: Shape = CircleShape,
    text: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    isEnabled: Boolean = true,
    containerColor: Color = black,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        contentPadding = contentPadding,
        shape = shape,
        enabled = isEnabled,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = white
        )
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            text?.let {
                text.invoke()
            }
            icon?.let {
                icon.invoke()
            }
        }
    }
}