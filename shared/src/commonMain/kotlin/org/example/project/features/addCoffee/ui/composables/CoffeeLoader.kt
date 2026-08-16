package org.example.project.features.addCoffee.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffee_loading
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.ExperimentalCompottieApi
import io.github.alexzhirkevich.compottie.Lottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.Resource
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kotlinx.coroutines.delay
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.textSecondaryColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun CoffeeInfoLoading(
    modifier: Modifier = Modifier,
) {
    var isLoaderVisible by remember { mutableStateOf(false) }
    var isTextVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoaderVisible = true
        delay(500.milliseconds)
        isTextVisible = true
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isLoaderVisible,
            enter = fadeIn() +
                    scaleIn(initialScale = 0.8f)
        ) {
            CoffeeLoader()
        }
        AnimatedVisibility(
            visible = isTextVisible,
            enter = fadeIn() + expandVertically()
        ) {
            RegularAppText(
                text = stringResource(Res.string.coffee_loading),
                color = textSecondaryColor,
                fontSize = 16.sp
            )
        }
    }
}


@OptIn(ExperimentalResourceApi::class, ExperimentalCompottieApi::class)
@Composable
fun CoffeeLoader(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Resource(Res.getUri("files/coffee_loader.json"))
    )

    Lottie(
        modifier = modifier,
        painter = rememberLottiePainter(
            composition = composition,
            iterations = Compottie.IterateForever,
            speed = 0.7f
        ),
        contentDescription = "Lottie animation"
    )
}

@OptIn(ExperimentalResourceApi::class, ExperimentalCompottieApi::class)
@Composable
fun RecipeLoader(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Resource(Res.getUri("files/recipe_loader.json"))
    )

    Lottie(
        modifier = modifier,
        painter = rememberLottiePainter(
            composition = composition,
            iterations = Compottie.IterateForever,
            speed = 0.7f
        ),
        contentDescription = "Lottie animation"
    )
}