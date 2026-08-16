package org.example.project.features.savedRecipes.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffee_amount_g
import coffee.shared.generated.resources.ic_clock_24
import coffee.shared.generated.resources.ic_coffee_beans_24
import coffee.shared.generated.resources.ic_temperature_24
import coffee.shared.generated.resources.ic_water_drip_24
import coffee.shared.generated.resources.temp_celsius
import coffee.shared.generated.resources.water_amount_ml_dot
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.mockRecipe
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SavedRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe = mockRecipe,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .height(200.dp)
            .background(white)
            .fillMaxWidth()
    ) {
        CoffeeImage(
            modifier = Modifier.aspectRatio(1/1.5f),
            model = recipe.coffee.imagePath
        )
        CardContent(
            modifier = Modifier.padding(20.dp),
            recipe = recipe
        )
    }
}

@Composable
private fun CardContent(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RegularAppText(
            text = recipe.title,
            fontSize = 18.sp,
            fontFamily = getMontserratBold(),
            maxLines = 2
        )
        RegularAppText(
            text = recipe.coffee.tasteDescription,
            fontSize = 13.sp,
            color = textSecondaryColor,
            maxLines = 2
        )
        RecipeComponentsRow(
            modifier = Modifier.fillMaxWidth(),
            coffeeAmount = recipe.coffeeAmount.toString(),
            waterAmount = recipe.waterAmount.toString(),
            temperature = recipe.waterTemperature.toString()
        )

    }
}

@Composable
private fun RecipeComponentsRow(
    modifier: Modifier = Modifier,
    coffeeAmount: String,
    waterAmount: String,
    temperature: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecipeComponentItem(
            painter = painterResource(Res.drawable.ic_coffee_beans_24),
            text = stringResource(Res.string.coffee_amount_g, coffeeAmount)
        )
        Spacer(Modifier.weight(1f))
        RecipeComponentItem(
            painter = painterResource(Res.drawable.ic_water_drip_24),
            text = stringResource(Res.string.water_amount_ml_dot, waterAmount)
        )
        Spacer(Modifier.weight(1f))
        RecipeComponentItem(
            painter = painterResource(Res.drawable.ic_temperature_24),
            text = stringResource(Res.string.temp_celsius, temperature)
        )
    }
}

@Composable
private fun RecipeComponentItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .alpha(0.8f)
                .size(22.dp),
            painter = painter,
            contentDescription = null
        )
        Spacer(Modifier.width(5.dp))
        RegularAppText(
            text = text,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun TimeRow(
    modifier: Modifier = Modifier,
    time: String,
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_clock_24),
            contentDescription = null,
        )
        RegularAppText(
            text = time,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun SavedRecipeCardPreview() {
    SavedRecipeCard()
}