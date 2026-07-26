package org.example.project.core.ui.navigation.voyager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundPrimary
import org.example.project.features.coffeeDetails.ui.composables.CoffeeDetailsScreen
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel

class CoffeeDetailsScreen(private val coffeeId: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Scaffold { paddingValues ->
            CoffeeDetailsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundPrimary)
                    .padding(
                        start = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        end = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
                screenModel = koinScreenModel<CoffeeDetailsScreenModel>(),
                coffeeId = coffeeId,
                onRecipeBtnClick = { coffeeId ->
                    navigator?.push(NewRecipeScreen(coffeeId))
                }
            )
        }
    }
}