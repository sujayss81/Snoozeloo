package org.sujay.snoozeloo

import android.app.Application
import org.koin.core.context.startKoin
import org.sujay.snoozeloo.core.di.appModule
import org.sujay.snoozeloo.core.service.ContextProvider
import org.sujay.snoozeloo.core.service.RealmDBService

class Snoozeloo: Application() {

    override fun onCreate() {
        super.onCreate()

        ContextProvider.initialize(this)
        RealmDBService.initialize()
        startKoin {
            modules(appModule)
        }
    }
}