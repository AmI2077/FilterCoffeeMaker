package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_coffee_image_placeholder
import coffee.shared.generated.resources.ic_load_coffee_photo
import coil3.compose.AsyncImage
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.blueGrayText
import org.jetbrains.compose.resources.painterResource

@Composable
fun CoffeeImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp),
    placeholder: Painter = painterResource(Res.drawable.ic_coffee_image_placeholder),
    model: Any? = null
) {
    AsyncImage(
        modifier = modifier
            .clip(shape),
        model = model,
        error = placeholder,
        contentScale = contentScale,
        contentDescription = null,
    )
}

@Preview
@Composable
fun CoffeeImagePlaceholder(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp),
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = { onClick() }
            )
            .border(
                width = 2.dp,
                color = blueGrayText,
                shape = shape
            )
    ) {
        Icon(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            painter = painterResource(Res.drawable.ic_load_coffee_photo),
            tint = blueGrayText,
            contentDescription = null,
        )
    }
}