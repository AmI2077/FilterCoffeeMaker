package org.example.project.core.di

import org.koin.dsl.module

val commonModule = module {
    includes(
        platformModule,
        dataModule,
        domainModule,
        vmModule,
    )
}