package org.example.project.core.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.ScreenModelStore
import io.ktor.http.parametersOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.example.project.core.domain.model.Recipe
import org.example.project.features.addCoffee.store.AddCoffeeStore
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel
import org.example.project.features.coffeeList.ui.vm.CoffeeScreenModel
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