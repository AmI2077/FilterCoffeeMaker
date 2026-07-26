package org.example.project.core.di

import org.example.project.features.coffeeList.domain.api.CoffeeInteractor
import org.example.project.features.coffeeList.domain.impl.CoffeeInteractorImpl
import org.example.project.features.newCoffee.domain.AddCoffeeInteractor
import org.example.project.features.newCoffee.domain.AddCoffeeInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AddCoffeeInteractor> {
        AddCoffeeInteractorImpl(get())
    }
    factory<CoffeeInteractor> {
        CoffeeInteractorImpl(get())
    }
}