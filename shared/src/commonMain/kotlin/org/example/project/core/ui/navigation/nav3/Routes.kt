package org.example.project.core.ui.navigation.nav3

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object RecentRecipesScreen : Routes {
    }

    @Serializable
    data object SavedRecipesScreen : Routes {
    }

    @Serializable
    data class RecipesDetailsScreen(val recipeId: Int) : Routes

    @Serializable
    data object CoffeeScreen : Routes {
    }

    @Serializable
    data class CoffeeDetailsScreen(val coffeeId: String) : Routes

    @Serializable
    data object NewRecipeScreen : Routes

    @Serializable
    data object AddCoffeeScreen : Routes

    @Serializable
    data class TimerScreen(val recipeId: Int) : Routes
}
