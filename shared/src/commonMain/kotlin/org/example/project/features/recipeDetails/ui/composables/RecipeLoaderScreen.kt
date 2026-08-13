package org.example.project.features.recipeDetails.ui.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.recipe_loading_image_2
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.features.addCoffee.ui.composables.RecipeLoader
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecipeLoaderScreen(
    modifier: Modifier = Modifier,
    screenModel: RecipeLoaderScreenModel,
) {
    val fact by screenModel.currentFact.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                    top = 100.dp
                )
                .align(Alignment.TopCenter)
        ) {
            Column(
                modifier = Modifier
            ) {
                RecipeLoader(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .aspectRatio(1 / 1f)
                        .align(Alignment.CenterHorizontally)
                )
                RegularAppText(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = textSecondaryColor,
                    text = "А ты знала, что...",
                )
                Spacer(Modifier.padding(top = 20.dp))
                AnimatedContent(
                    targetState = fact,
                ) { targetFact ->
                    RegularAppText(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        maxLines = Int.MAX_VALUE,
                        textAlign = TextAlign.Center,
                        text = targetFact,
                    )
                }
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(Res.drawable.recipe_loading_image_2),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}