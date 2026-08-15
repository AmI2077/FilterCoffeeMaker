package org.example.project.features.recipesList.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffee_amount_g
import coffee.shared.generated.resources.coffee_label
import coffee.shared.generated.resources.ic_coffee_beans_24
import coffee.shared.generated.resources.ic_cup_24
import coffee.shared.generated.resources.ic_temperature_24
import coffee.shared.generated.resources.recipe_card_time
import coffee.shared.generated.resources.temp_celsius
import coffee.shared.generated.resources.temp_label
import coffee.shared.generated.resources.water_amount_ml_dot
import coffee.shared.generated.resources.water_label
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.mockRecipe
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.white
import org.example.project.core.utils.toTimeString
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe = mockRecipe,
    onRecipeClick: (recipe: Recipe) -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = { onRecipeClick(recipe) }
            )
            .width(160.dp)
            .shadow(
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp,
                spotColor = black.copy(alpha = 0.3f),
                ambientColor = black.copy(alpha = 0.3f),
            )
            .background(
                color = white,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        CoffeeImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1 / 1f),
            model = recipe.coffee.imagePath
        )
        Spacer(Modifier.padding(top = 15.dp))
        RegularAppText(
            text = recipe.title,
            fontSize = 18.sp,
            maxLines = 2,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.padding(top = 10.dp))
        RegularAppText(
            text = stringResource(Res.string.recipe_card_time, recipe.brewTime.toTimeString()),
            fontSize = 16.sp,
            maxLines = 2,
            color = textSecondaryColor
        )
        Spacer(Modifier.padding(top = 20.dp))
        DetailsColumn(
            coffeeAmount = recipe.coffeeAmount,
            waterAmount = recipe.waterAmount,
            temperature = recipe.waterTemperature
        )
    }
}

@Composable
fun DetailsColumn(
    modifier: Modifier = Modifier,
    coffeeAmount: Int,
    waterAmount: Int,
    temperature: Int,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.ic_coffee_beans_24),
                contentDescription = null
            )
            Spacer(Modifier.padding(start = 5.dp))
            RegularAppText(
                text = stringResource(Res.string.coffee_label),
                fontSize = 12.sp,
                color = textSecondaryColor
            )
            Spacer(Modifier.weight(1f))
            RegularAppText(
                text = stringResource(Res.string.coffee_amount_g, coffeeAmount),
                fontSize = 14.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.ic_cup_24),
                contentDescription = null
            )
            Spacer(Modifier.padding(start = 5.dp))
            RegularAppText(
                text = stringResource(Res.string.water_label),
                fontSize = 12.sp,
                color = textSecondaryColor
            )
            Spacer(Modifier.weight(1f))
            RegularAppText(
                text = stringResource(Res.string.water_amount_ml_dot, waterAmount),
                fontSize = 14.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(Res.drawable.ic_temperature_24),
                contentDescription = null
            )
            Spacer(Modifier.padding(start = 5.dp))
            RegularAppText(
                text = stringResource(Res.string.temp_label),
                fontSize = 12.sp,
                color = textSecondaryColor
            )
            Spacer(Modifier.weight(1f))
            RegularAppText(
                text = stringResource(Res.string.temp_celsius, temperature),
                fontSize = 14.sp
            )
        }
    }
}