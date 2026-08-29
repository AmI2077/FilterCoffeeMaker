package org.example.project

import android.app.Application
import org.example.project.core.di.initKoin
import org.koin.android.ext.koin.androidContext


class AndroidApp : Application() {
    override fun onCreate() {
        initKoin {
            androidContext(this@AndroidApp)
        }
        super.onCreate()
    }
}
