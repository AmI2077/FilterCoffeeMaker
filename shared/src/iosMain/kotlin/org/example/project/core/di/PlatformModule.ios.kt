package org.example.project.core.di

import org.example.project.core.data.local.db.AppDatabase
import org.example.project.core.data.local.db.getRoomDatabase
import org.example.project.core.domain.api.ImageSaver
import org.example.project.features.addCoffee.data.ImageSaverImpl
import org.example.project.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> {
            getRoomDatabase(
                getDatabaseBuilder()
            )
        }

        single<ImageSaver> { ImageSaverImpl() }
    }