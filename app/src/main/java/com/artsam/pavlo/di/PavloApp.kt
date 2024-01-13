package com.artsam.pavlo.di

import android.app.Application
import android.os.StrictMode
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PavloApp : Application() {

    override fun onCreate() {
        super.onCreate()
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().build())
        initializeKoin()
    }

    private fun initializeKoin() = startKoin {
        androidContext(this@PavloApp)
        modules(
            transactionModule,
        )
    }
}