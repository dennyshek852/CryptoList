package com.project.cryptolist.data

import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocal
import com.project.cryptolist.data.repository.CurrencyRepository
import com.project.cryptolist.data.repository.CurrencyRepositoryImpl
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
class CurrencyRepositoryTest {

    //Mock Dao for dependency of datasource
    private lateinit var mockDataSourceLocal: CurrencyDataSourceLocal

    //The class we are going to test
    private lateinit var currencyRepository: CurrencyRepository

    @Before
    fun setup() {
        mockDataSourceLocal = mockk<CurrencyDataSourceLocal>()
        currencyRepository = CurrencyRepositoryImpl(mockDataSourceLocal)
        //Given
        coEvery { mockDataSourceLocal.insertCurrencyList(any()) } just Runs
        coEvery { mockDataSourceLocal.deleteCurrencyList() } just Runs
    }

    @Test
    fun test_getCurrencyList_return_empty_list() = runTest(StandardTestDispatcher()) {
        // Given a stub function for `getCurrencyList()`.
        coEvery { mockDataSourceLocal.getCurrencyList() } returns emptyList()

        // When calling the function on our class.
        val result = currencyRepository.getCurrencyList()

        // Then verify that the function was called on our DAO.
        coVerify { mockDataSourceLocal.getCurrencyList() }
        // And the result is what our DAO function stub returned.
        assert(result.isEmpty())
    }

    @Test
    fun test_getCurrencyList_return_valid_list() = runTest(StandardTestDispatcher()) {
        // Given a stub function for `getCurrencyList()`.
        coEvery { mockDataSourceLocal.getCurrencyList() } returns mockFullDataList

        // When calling the function on our class.
        val result = currencyRepository.getCurrencyList()

        // Then verify that the function was called on our datasource.
        coVerify { mockDataSourceLocal.getCurrencyList() }
        // And the result is what our datasource function stub returned.
        assert(result == mockFullDataList)
    }

    @Test
    fun test_insertCurrencyList_valid() = runTest(StandardTestDispatcher()) {
        //When
        currencyRepository.insertCurrencyList(mockFullDataList)
        //Then
        coVerify { mockDataSourceLocal.insertCurrencyList(mockFullDataList) }
    }

    @Test
    fun test_deleteCurrencyList_valid() = runTest(StandardTestDispatcher()) {
        //When
        currencyRepository.deleteCurrencyList()
        //Then
        coVerify { mockDataSourceLocal.deleteCurrencyList() }
    }
}