package com.project.cryptolist.data

import com.project.cryptolist.data.datasource.local.currency.CurrencyDao
import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocalImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.project.cryptolist.MockDataHelper.Companion.mockFullDataList

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyDataSourceLocalImplTest {

    //Mock Dao for dependency of datasource
    private lateinit var mockDao: CurrencyDao

    //The class we are going to test
    private lateinit var datasource: CurrencyDataSourceLocalImpl

    @Before
    fun setup() {
        mockDao = mockk<CurrencyDao>()
        datasource = CurrencyDataSourceLocalImpl(mockDao)
        //Given
        coEvery { mockDao.insertCurrencyList(any()) } just Runs
        coEvery { mockDao.deleteCurrencyList() } just Runs
    }

    @Test
    fun test_getCurrencyList_returns_empty_list() = runTest(StandardTestDispatcher()) {
        // Given a stub function for `getCurrencyList()`.
        coEvery { mockDao.getCurrencyList() } returns emptyList()

        // When calling the function on our class.
        val result = datasource.getCurrencyList()

        // Then verify that the function was called on our DAO.
        coVerify { mockDao.getCurrencyList() }
        // And the result is what our DAO function stub returned.
        assert(result.isEmpty())
    }

    @Test
    fun test_getCurrencyList_return_valid_list() = runTest(StandardTestDispatcher()) {
        // Given a stub function for `getCurrencyList()`.
        coEvery { mockDao.getCurrencyList() } returns mockFullDataList

        // When calling the function on our class.
        val result = datasource.getCurrencyList()

        // Then verify that the function was called on our DAO.
        coVerify { mockDao.getCurrencyList() }
        // And the result is what our DAO function stub returned.
        assert(result == mockFullDataList)
    }

    @Test
    fun test_insertCurrencyList_valid() = runTest(StandardTestDispatcher()) {
        //When
        datasource.insertCurrencyList(mockFullDataList)
        //Then
        coVerify { mockDao.insertCurrencyList(mockFullDataList) }
    }

    @Test
    fun test_deleteCurrencyList_valid() = runTest(StandardTestDispatcher()) {
        //When
        datasource.deleteCurrencyList()
        //Then
        coVerify { mockDao.deleteCurrencyList() }
    }
}