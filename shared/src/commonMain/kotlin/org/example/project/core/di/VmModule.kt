package org.example.project.core.di

import org.example.project.core.domain.model.Recipe
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel
import org.example.project.features.savedCoffee.ui.vm.SavedCoffeeScreenModel
import org.example.project.features.addCoffee.ui.vm.AddCoffeeScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeDetailsScreenModel
import org.example.project.features.recipeDetails.ui.vm.RecipeLoaderScreenModel
import org.example.project.features.recipesList.ui.vm.RecipesScreenModel
import org.example.project.features.timer.ui.vm.TimerScreenModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val vmModule = module {
    includes(storeModule)

    factory {
        AddCoffeeScreenModel { scope ->
            get<AddCoffeeStore> {
                parametersOf(scope)
            }
        }
    }
    factory {
        SavedCoffeeScreenModel(get(), get())
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
    factory { (coffeeId: Int?, recipe: Recipe?) ->
        RecipeDetailsScreenModel(
            coffeeId = coffeeId,
            recipe = recipe,
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