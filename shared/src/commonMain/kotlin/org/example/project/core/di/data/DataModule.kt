package org.example.project.core.di.data

import org.example.project.core.data.local.db.AppDatabase
import org.example.project.core.data.local.db.dao.CoffeeDao
import org.example.project.core.data.local.db.dao.FavouritesRecipesDao
import org.example.project.core.data.local.db.dao.RecentRecipesDao
import org.example.project.core.data.resources.ResourceManagerImpl
import org.example.project.core.domain.api.ResourceManager
import org.koin.dsl.module

val dataModule = module {
    includes(repositoryModule)
    includes(networkModule)

    single<ResourceManager> {
        ResourceManagerImpl()
    }

    single<CoffeeDao> { get<AppDatabase>().getCoffeeDao() }
    single<FavouritesRecipesDao> { get<AppDatabase>().getFavouritesDao() }
    single<RecentRecipesDao> { get<AppDatabase>().getRecipeDao() }
}