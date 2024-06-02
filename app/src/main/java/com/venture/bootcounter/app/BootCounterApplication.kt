package com.venture.bootcounter.app

import android.app.Application
import com.venture.bootcounter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BootCounterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BootCounterApplication)
            modules(appModule)
        }
    }
}