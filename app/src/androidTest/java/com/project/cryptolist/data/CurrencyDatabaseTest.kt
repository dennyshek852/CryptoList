package com.project.cryptolist.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.project.cryptolist.data.datasource.local.currency.CurrencyDao
import com.project.cryptolist.data.datasource.local.currency.CurrencyDatabase
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyDatabaseTest {

    //dao
    private lateinit var currencyDao: CurrencyDao

    //The class we are going to test
    private lateinit var currencyDatabase: CurrencyDatabase


    @Before
    fun setup() {
        currencyDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CurrencyDatabase::class.java
        ).build()
        currencyDao = currencyDatabase.currencyDao()
    }

    @Test
    fun testDatabaseIsCreated() = runTest(StandardTestDispatcher()) {
        //given
        val mockData = listOf(
            CurrencyInfo(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC"
            ), CurrencyInfo(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
                code = "USD"
            )
        )
        //When
        currencyDao.insertCurrencyList(mockData)
        //Then
        val result = currencyDao.getCurrencyList()
        assert(result == mockData)
    }

}