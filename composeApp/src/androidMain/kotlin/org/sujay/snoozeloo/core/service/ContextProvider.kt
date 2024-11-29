package org.sujay.snoozeloo.core.service

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextProvider {
    private lateinit var context: Context

    fun initialize(appContext: Context) {
        context = appContext
    }

    fun getContext(): Context {
        if (!::context.isInitialized) {
            throw IllegalStateException("ContextProvider not initialized!")
        }
        return context
    }
}