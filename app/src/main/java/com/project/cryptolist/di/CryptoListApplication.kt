package com.project.cryptolist.di

import android.app.Application
import com.project.cryptolist.di.module.dataModule
import com.project.cryptolist.di.module.domainModule
import com.project.cryptolist.di.module.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoListApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin {
            // declare used android context
            androidContext(this@CryptoListApplication)
            // declare modules
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }

}