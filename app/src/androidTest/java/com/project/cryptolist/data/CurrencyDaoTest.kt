package com.project.cryptolist.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.project.cryptolist.data.datasource.local.currency.CurrencyDao
import com.project.cryptolist.data.datasource.local.currency.CurrencyDatabase
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyDaoTest {

    //Mock database
    private lateinit var database: CurrencyDatabase

    //The class we are going to test
    private lateinit var currencyDao: CurrencyDao

    private val mockUsdData = CurrencyInfo(
        id = "USD",
        name = "United States Dollar",
        symbol = "$",
        code = "USD"
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CurrencyDatabase::class.java).build()
        currencyDao = database.currencyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws
    fun test_getCurrencyList_returns_inserted_list() = runTest(StandardTestDispatcher()) {
        // Given testing data insert into database
        val mockData = listOf(mockUsdData)
        currencyDao.insertCurrencyList(mockData)

        // When calling the function on our class.
        val insertedData = currencyDao.getCurrencyList()

        // Then verify insertedData is not empty and has the correct size
        assert(insertedData.isNotEmpty())
        assert(insertedData.size == mockData.size)
        assert(mockData == insertedData)
    }

    @Test
    @Throws
    fun test_getCurrencyList_returns_empty_list() = runTest(StandardTestDispatcher()) {
        // Given testing data insert into database
        currencyDao.insertCurrencyList(listOf())

        // When calling the function on our class.
        val result = currencyDao.getCurrencyList()

        //Then verify result expected
        assert(listOf<CurrencyInfo>() == result)
    }


    @Test
    @Throws
    fun test_deleteCurrencyList_returns_empty_list() = runTest(StandardTestDispatcher()) {
        // Given testing data.
        val mockData = listOf(mockUsdData, mockUsdData)
        currencyDao.insertCurrencyList(mockData)

        // When calling the function on our class.

        currencyDao.deleteCurrencyList()
        val result = currencyDao.getCurrencyList()

        // Then verify insertedData has been deleted
        assert(result.isEmpty())
    }

}