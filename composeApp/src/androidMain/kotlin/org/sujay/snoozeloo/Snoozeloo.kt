package org.sujay.snoozeloo

import android.app.Application
import org.koin.core.context.startKoin
import org.sujay.snoozeloo.core.di.appModule

class Snoozeloo: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}