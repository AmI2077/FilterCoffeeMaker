package org.example.project.features.savedCoffee.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_ai_24
import coffee.shared.generated.resources.ic_coffee_image_placeholder
import coffee.shared.generated.resources.recipe
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.gray
import org.example.project.core.ui.theme.white
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoffeeItem(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
    onClick: (coffeeId: String) -> Unit,
    onLongClick: (coffee: Coffee) -> Unit
) {
    Row(
        modifier = modifier
            .height(240.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = black.copy(alpha = 0.3f),
                ambientColor = black.copy(alpha = 0.3f),
            )
            .background(
                color = white,
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)
            )
            .combinedClickable(
                onClick = { onClick(coffee.id) },
                onLongClick = { onLongClick(coffee) }
            )
            .padding(10.dp)
    ) {
        CoffeeImage(
            modifier = Modifier
                .aspectRatio(1 / 1.4f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)),
            contentScale = ContentScale.Crop,
            model = coffee.imagePath,
            placeholder = painterResource(Res.drawable.ic_coffee_image_placeholder)
        )
        CoffeeItemContent(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight(),
            roasting = coffee.roasting,
            title = coffee.title,
            tasteDescription = coffee.tasteDescription,
            processingMethod = coffee.processingMethod,
        ) {
            onRecipeBtnClick(coffee.id)
        }
    }
}

@Composable
fun CoffeeItemContent(
    modifier: Modifier = Modifier,
    roasting: String,
    title: String,
    tasteDescription: String,
    processingMethod: String,
    onRecipeClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.padding(top = 5.dp))
        RegularAppText(
            text = roasting,
            fontSize = 14.sp,
            color = blueGrayText
        )
        Spacer(Modifier.padding(top = 15.dp))
        RegularAppText(
            text = title,
            fontSize = 20.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.padding(top = 10.dp))
        RegularAppText(
            text = tasteDescription,
            fontSize = 14.sp,
            maxLines = 2
        )

        Spacer(modifier = Modifier.weight(1f))

        RegularAppText(
            text = "Обработка: $processingMethod",
            fontSize = 14.sp,
            color = gray,
        )
        Spacer(Modifier.padding(top = 10.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp),
                    painter = painterResource(Res.drawable.ic_ai_24),
                    contentDescription = null
                )
            },
            text = {
                RegularAppText(
                    text = stringResource(Res.string.recipe),
                    fontSize = 16.sp,
                    color = white
                )
            },
            onClick = {
                onRecipeClick()
            }
        )
    }
}