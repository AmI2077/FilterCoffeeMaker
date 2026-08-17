package org.example.project.features.savedCoffee.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_ai_24
import coffee.shared.generated.resources.ic_coffee_image_placeholder
import coffee.shared.generated.resources.processing_prefix
import coffee.shared.generated.resources.recipe
import org.example.project.core.domain.model.Coffee
import org.example.project.core.domain.model.mockCoffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.getMontserratMedium
import org.example.project.core.ui.theme.shadowColor
import org.example.project.core.ui.theme.textPrimaryColorLight
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Preview(
    widthDp = 393,
)
@Composable
fun CoffeeCardPreview() {
    CoffeeCard(
        coffee = mockCoffee,
        onClick = {},
        onLongClick = {},
        onRecipeBtnClick = {}
    )
}

@Composable
fun CoffeeCard(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
    onClick: (coffeeId: String) -> Unit,
    onLongClick: (coffee: Coffee) -> Unit
) {
    Row(
        modifier = modifier
            .height(220.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(UiDefaults.CARD_CORNERS_RADIUS.dp),
                spotColor = shadowColor,
                ambientColor = shadowColor,
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)
            )
            .combinedClickable(
                onClick = { onClick(coffee.id) },
                onLongClick = { onLongClick(coffee) }
            )
    ) {
        CoffeeImage(
            modifier = Modifier
                .width(150.dp)
                .clip(RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)),
            contentScale = ContentScale.Crop,
            model = coffee.imagePath,
            placeholder = painterResource(Res.drawable.ic_coffee_image_placeholder)
        )
        CoffeeCardContent(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(5.dp),
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
fun CoffeeCardContent(
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
        RegularAppText(
            text = roasting,
            fontSize = UiDefaults.CARD_SMALL_TEXT_SIZE.sp,
            color = textSecondaryColor
        )
        Spacer(Modifier.height(5.dp))
        RegularAppText(
            text = title,
            fontSize = UiDefaults.CARD_HEADER_TEXT_SIZE.sp,
            fontFamily = getMontserratBold(),
            maxLines = 2
        )
        Spacer(Modifier.height(10.dp))
        RegularAppText(
            text = tasteDescription,
            fontSize = UiDefaults.CARD_REGULAR_TEXT_SIZE.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.weight(1f))
        RegularAppText(
            text = stringResource(Res.string.processing_prefix, processingMethod),
            fontSize = UiDefaults.CARD_REGULAR_TEXT_SIZE.sp,
            color = textSecondaryColor,
        )
        Spacer(Modifier.height(10.dp))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_ai_24),
                    contentDescription = null
                )
            },
            text = {
                RegularAppText(
                    text = stringResource(Res.string.recipe),
                    fontSize = UiDefaults.CARD_REGULAR_TEXT_SIZE.sp,
                    color = textPrimaryColorLight,
                    fontFamily = getMontserratMedium()
                )
            },
            onClick = {
                onRecipeClick()
            }
        )
    }
}