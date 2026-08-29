package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.domain.model.Recipe
import org.example.project.core.domain.model.mockRecipe
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.textPrimaryColorDark
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.utils.toTimeString
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.example.project.features.recipeDetails.ui.vm.RecipeDetailsScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_fav_24
import coffee.shared.generated.resources.ic_no_fav_24
import coffee.shared.generated.resources.make_button
import coffee.shared.generated.resources.recipe_total_time
import coffee.shared.generated.resources.steps_brewing_title
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.lightOrange
import org.example.project.core.ui.theme.textPrimaryColorLight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    screenModel: RecipeDetailsScreenModel,
    loaderScreenModel: RecipeLoaderScreenModel,
    onStartTimerClick: (recipe: Recipe) -> Unit,
) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    println("_STATE: $state")

    val scrollState = rememberScrollState()

    if (state.showWaterAmountDialog) {
        WaterAmountDialog { amount ->
            screenModel.loadRecipeFromAi(amount)
        }
    }

    if (state.isLoading) {
        RecipeLoaderScreen(
            modifier = modifier
                .background(backgroundColor),
            screenModel = loaderScreenModel
        )
    }

    if (state.content != null) {
        RecipeDetailsScreenContent(
            modifier = modifier
                .verticalScroll(scrollState),
            recipe = state.content!!,
            onStartTimerClick = { recipe ->
                onStartTimerClick(recipe)
            }
        )
    }
}

@Preview
@Composable
fun RecipeDetailsScreenContent(
    modifier: Modifier = Modifier,
    recipe: Recipe = mockRecipe,
    isFavourite: Boolean = false,
    onStartTimerClick: (recipe: Recipe) -> Unit = {}
) {
    println("IMAGE_DIRECTORY: ${recipe.coffee.imagePath}")
    Column(
        modifier = modifier
    ) {
        CoffeeImage(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .aspectRatio(1 / 1f),
            model = recipe.coffee.imagePath
        )
        Spacer(Modifier.padding(top = 20.dp))
        Column(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(
                        topStart = UiDefaults.IMAGE_CORNERS_RADIUS.dp,
                        topEnd = UiDefaults.IMAGE_CORNERS_RADIUS.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp,
                    )
                )
                .padding(horizontal = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RegularAppText(
                    modifier = Modifier.weight(0.5f),
                    text = "${recipe.title} V60",
                    fontFamily = getMontserratBold(),
                    fontSize = 20.sp,
                    color = textPrimaryColorDark,
                    maxLines = 2
                )
                Spacer(Modifier.weight(0.1f))
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    painter = if (isFavourite) {
                        painterResource(Res.drawable.ic_fav_24)
                    } else  painterResource(Res.drawable.ic_no_fav_24),
                    contentDescription = null,
                    tint = if (isFavourite) lightOrange else black
                )
            }

            Column(
                modifier = Modifier
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Row {
                    RegularAppText(
                        text = stringResource(Res.string.recipe_total_time),
                        color = textSecondaryColor,
                    )
                    RegularAppText(
                        text = recipe.brewTime.toTimeString(),
                        color = textPrimaryColorDark,
                        fontFamily = getMontserratBold()
                    )
                }
                Spacer(Modifier.height(10.dp))
                HorizontalDivider(
                    color = textSecondaryColor.copy(alpha = 0.4f)
                )
                Spacer(Modifier.height(15.dp))
                ComponentsRow(
                    coffeeAmount = "${recipe.coffeeAmount} г.",
                    waterAmount = "${recipe.waterAmount} мл.",
                    temperature = "${recipe.waterTemperature} °C",
                )
            }
            RegularAppText(
                text = stringResource(Res.string.steps_brewing_title),
                fontFamily = getMontserratBold(),
                fontSize = 20.sp,
                color = textPrimaryColorDark
            )
            Column(
                Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                recipe.brewSteps.forEachIndexed { index, step ->
                    BrewStep(
                        modifier = Modifier.padding(vertical = 10.dp),
                        number = index + 1,
                        startTime = step.startTime,
                        endTime = step.endTime,
                        waterAmount = step.amountWater
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            AppButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = {
                    RegularAppText(
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                        text = stringResource(Res.string.make_button),
                        color = textPrimaryColorLight
                    )
                },
                icon = null
            ) {
                onStartTimerClick(recipe)
            }
        }
    }
}