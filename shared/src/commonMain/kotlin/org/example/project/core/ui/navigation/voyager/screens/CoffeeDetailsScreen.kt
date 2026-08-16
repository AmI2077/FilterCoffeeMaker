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
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.features.coffeeDetails.store.CoffeeDetailsScreenModel
import org.example.project.features.coffeeDetails.ui.composables.CoffeeDetailsScreen
import org.example.project.features.editCoffee.store.EditCoffeeScreenModel
import org.example.project.features.editCoffee.ui.composables.EditBottomSheet
import org.koin.core.parameter.parametersOf

class CoffeeDetailsScreen(private val coffeeId: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Scaffold { paddingValues ->
            CoffeeDetailsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(
                        start = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        end = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
                detailsScreenModel = koinScreenModel<CoffeeDetailsScreenModel>(),
                editScreenModel = koinScreenModel<EditCoffeeScreenModel>(),
                coffeeId = coffeeId,
                onRecipeBtnClick = { coffeeId ->
                    navigator?.push(NewRecipeScreen(coffeeId))
                },
                onBackBtnClick = { navigator?.pop() }
            )
        }
    }
}