package org.example.project.core.di

import org.example.project.core.domain.model.Recipe
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel
import org.example.project.features.coffeeList.ui.vm.CoffeeScreenModel
import org.example.project.features.newCoffee.ui.vm.AddCoffeeScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeDetailsScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel
import org.example.project.features.recipesList.ui.vm.RecipesScreenModel
import org.example.project.features.timer.ui.vm.TimerScreenModel
import org.koin.dsl.module

val vmModule = module {
    factory {
        AddCoffeeScreenModel(get(), get())
    }
    factory {
        CoffeeScreenModel(get(), get())
    }
    factory {
        CoffeeDetailsScreenModel(get(), get())
    }
    factory {
        RecipesScreenModel(
            get(),
            get(),
        )
    }
    factory { (coffeeId: Int) ->
        RecipeDetailsScreenModel(
            coffeeId = coffeeId,
            imageSaver = get(),
            recipeDetailsRepository = get(),
            coffeeDetailsRepository = get()
        )
    }
    factory { (recipe: Recipe) ->
        TimerScreenModel(recipe)
    }
    factory {
        RecipeLoaderScreenModel(get())
    }
}