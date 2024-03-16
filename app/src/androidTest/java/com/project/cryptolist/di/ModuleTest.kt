package com.project.cryptolist.di

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.project.cryptolist.di.module.dataModule
import com.project.cryptolist.di.module.domainModule
import com.project.cryptolist.di.module.presentationModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules


class ModuleTest : KoinTest {

    @Before
    fun setUp() {
        stopKoin() // ensure the Koin context is cleaned up before each test
    }

    @Test
    fun checkAllModules() {
        checkModules {
            androidContext(ApplicationProvider.getApplicationContext<Application>())
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}