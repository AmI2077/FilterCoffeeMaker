package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.textPrimaryColor
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.core.ui.theme.white
import org.example.project.core.utils.toTimeString
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.example.project.features.recipeDetails.ui.state.RecipeDetailsScreenIntent.LoadRecipeDetails
import org.example.project.features.recipeDetails.ui.state.RecipeDetailsScreenUiState
import org.example.project.features.recipeDetails.ui.vm.RecipeDetailsScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    screenModel: RecipeDetailsScreenModel,
    loaderScreenModel: RecipeLoaderScreenModel,
    onStartTimerClick: (recipe: Recipe) -> Unit,
) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    println("_STATE: ${state}")

    val scrollState = rememberScrollState()

    when (state) {
        is RecipeDetailsScreenUiState.Content -> {
            val recipe = (state as RecipeDetailsScreenUiState.Content).recipe
            RecipeDetailsScreenContent(
                modifier = modifier
                    .verticalScroll(scrollState),
                coffeeImage = (state as RecipeDetailsScreenUiState.Content).imagePath,
                recipe = recipe,
                onStartTimerClick = { recipe ->
                    onStartTimerClick(recipe)
                }
            )
        }

        RecipeDetailsScreenUiState.Loading -> {
            RecipeLoaderScreen(
                modifier = modifier
                    .background(white),
                screenModel = loaderScreenModel
            )
        }

        RecipeDetailsScreenUiState.WaterAmountDialog -> {
            WaterAmountDialog { amount ->
                screenModel.onIntent(
                    LoadRecipeDetails(waterAmount = amount)
                )
            }
        }

        else -> Unit
    }
}

@Preview
@Composable
fun RecipeDetailsScreenContent(
    modifier: Modifier = Modifier,
    coffeeImage: String? = null,
    recipe: Recipe = mockRecipe,
    onStartTimerClick: (recipe: Recipe) -> Unit = {}
) {
    println("IMAGE_DIRECTORY: $coffeeImage")
    Column(
        modifier = modifier
    ) {
        CoffeeImage(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .aspectRatio(1 / 1f),
            model = coffeeImage
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
            Spacer(Modifier.padding(top = 10.dp))
            Column(
                modifier = Modifier
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = white,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
            ) {
                Row {
                    RegularAppText(
                        text = "Время: ",
                        color = textSecondaryColor,
                    )
                    RegularAppText(
                        text = recipe.brewTime.toTimeString(),
                        color = textPrimaryColor,
                        fontFamily = getComfortaBold()
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
                text = "Шаги заваривания",
                fontFamily = getComfortaBold(),
                fontSize = 24.sp,
                color = textPrimaryColor

            )
            Column(
                Modifier
                    .background(
                        color = white,
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
                        text = "Делаем",
                        color = white
                    )
                },
                icon = null
            ) {
                onStartTimerClick(recipe)
            }
        }
    }
}