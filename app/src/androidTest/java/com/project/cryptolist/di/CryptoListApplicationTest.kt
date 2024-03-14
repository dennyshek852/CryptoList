package com.project.cryptolist.di

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.project.cryptolist.di.CryptoListApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class CryptoListApplicationTest {

    @Test
    fun testStartKoinCalledInOnCreate() {
        ApplicationProvider.getApplicationContext<CryptoListApplication>()
        Assert.assertNotNull(GlobalContext.getOrNull())
    }
}