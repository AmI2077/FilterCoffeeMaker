package org.example.project.core.di

import org.example.project.core.domain.api.AppLogger
import org.example.project.core.domain.impl.AppLoggerImpl
import org.example.project.features.savedCoffee.domain.api.CoffeeInteractor
import org.example.project.features.savedCoffee.domain.impl.CoffeeInteractorImpl
import org.example.project.features.addCoffee.domain.AddCoffeeInteractor
import org.example.project.features.addCoffee.domain.AddCoffeeInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AddCoffeeInteractor> {
        AddCoffeeInteractorImpl(get())
    }
    factory<CoffeeInteractor> {
        CoffeeInteractorImpl(get())
    }

    single<AppLogger> {
        AppLoggerImpl()
    }
}