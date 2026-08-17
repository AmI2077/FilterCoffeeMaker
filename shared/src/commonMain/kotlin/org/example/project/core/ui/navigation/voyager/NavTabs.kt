package org.example.project.core.ui.navigation.voyager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.coffeeScreenTitle
import coffee.shared.generated.resources.ic_cup_24
import coffee.shared.generated.resources.ic_filter_recepies_screen_24
import coffee.shared.generated.resources.ic_mycoffee_screen_24
import coffee.shared.generated.resources.ic_saved_recipes_screen_24
import coffee.shared.generated.resources.mainScreenTitle
import coffee.shared.generated.resources.recipeScreenTitle
import org.example.project.core.ui.navigation.voyager.screens.AddCoffeeScreen
import org.example.project.core.ui.navigation.voyager.screens.CoffeeDetailsScreen
import org.example.project.core.ui.navigation.voyager.screens.NewRecipeScreen
import org.example.project.core.ui.theme.white
import org.example.project.features.savedCoffee.ui.screens.SavedCoffeeScreen
import org.example.project.features.recentRecipes.ui.composables.RecipesScreen
import org.example.project.features.recentRecipes.ui.vm.RecipesScreenModel
import org.example.project.features.savedCoffee.store.SavedCoffeeScreenModel
import org.example.project.features.savedRecipes.ui.composables.SavedRecipesScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object RecentRecipesTab : Tab {
    @Composable
    override fun Content() {
        val tabNavigator = LocalNavigator.current
        val root = tabNavigator?.parent

        RecipesScreen(
            modifier = Modifier
                .fillMaxSize(),
            screenModel = koinScreenModel<RecipesScreenModel>()
        ) {
            root?.push(NewRecipeScreen(
                coffeeId = null,
                recipe = it
            ))
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(Res.drawable.ic_saved_recipes_screen_24)
            val title = stringResource(Res.string.mainScreenTitle)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}

object SavedRecipesTab : Tab {
    @Composable
    override fun Content() {
        SavedRecipesScreen(
            modifier = Modifier
                .background(white)
                .fillMaxSize()
        )
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(Res.drawable.ic_filter_recepies_screen_24)
            val title = stringResource(Res.string.recipeScreenTitle)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}

object CoffeeTab : Tab {
    @Composable
    override fun Content() {
        val tabNavigator = LocalNavigator.current
        val root = tabNavigator?.parent
        SavedCoffeeScreen(
            onAddCoffeeClick = {
                root?.push(AddCoffeeScreen())
            },
            onRecipeBtnClick = {},
            onItemClick = { coffeeId ->
                root?.push(CoffeeDetailsScreen(coffeeId))
            },
            screenModel = koinScreenModel<SavedCoffeeScreenModel>()
        )
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(Res.drawable.ic_mycoffee_screen_24)
            val title = stringResource(Res.string.coffeeScreenTitle)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}