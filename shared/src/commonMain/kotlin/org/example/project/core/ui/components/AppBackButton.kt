package org.example.project.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_back_button_24
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.white
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(
                color = backgroundColor.copy(alpha = 0.6f),
            )
            .border(
                width = 1.dp,
                color = white,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                onClick()
            }
            .padding(10.dp),
    ) {

        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(Res.drawable.ic_back_button_24),
            contentDescription = null
        )
    }
}