package com.project.cryptolist.domain

import com.project.cryptolist.MockDataHelper.Companion.mockFullDataList
import com.project.cryptolist.data.repository.CurrencyRepository
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCaseImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyUseCaseTest {

    //Mock Dao for dependency of datasource
    private lateinit var mockCurrencyRepository: CurrencyRepository

    //The class we are going to test
    private lateinit var currencyUseCase: CurrencyUseCase

    @Before
    fun setup() {
        mockCurrencyRepository = mockk<CurrencyRepository>()
        currencyUseCase = CurrencyUseCaseImpl(mockCurrencyRepository)
    }

    @Test
    fun test_getStubData_returns_valid_list() = runTest(StandardTestDispatcher()) {
        //Given
        //When
        val result = currencyUseCase.getStubCurrencyList()
        //Then
        assert(result == mockFullDataList)
    }

    @Test
    fun test_clearCurrencyList() = runTest(StandardTestDispatcher()) {
        //Given
        coEvery { mockCurrencyRepository.deleteCurrencyList() } just Runs
        //When
        currencyUseCase.clearCurrencyList()
        //Then verify that the function was called on our repository.
        coVerify { mockCurrencyRepository.deleteCurrencyList() }
    }

    @Test
    fun test_insertCurrencyList() = runTest(StandardTestDispatcher()) {
        //Given
        coEvery { mockCurrencyRepository.insertCurrencyList(any()) } just Runs
        // When
        currencyUseCase.insertCurrencyList(mockFullDataList)
        // Then verify that the function was called on our repository.
        coVerify { mockCurrencyRepository.insertCurrencyList(any()) }
    }

    @Test
    fun test_getCurrencyList_returns_valid_list() = runTest(StandardTestDispatcher()) {
        // Given
        coEvery { mockCurrencyRepository.getCurrencyList() } returns mockFullDataList
        // When
        val result = currencyUseCase.getCurrencyList()
        // Then verify that the function was called on our repository.
        coVerify { mockCurrencyRepository.getCurrencyList() }
        assert(result == mockFullDataList)
    }

    @Test
    fun test_displayCurrencyList_returns_empty_list() = runTest(StandardTestDispatcher()) {
        // Given
        coEvery { mockCurrencyRepository.getCurrencyList() } returns listOf()
        // When
        val result = currencyUseCase.getCurrencyList()
        // Then verify that the function was called on our repository.
        coVerify { mockCurrencyRepository.getCurrencyList() }
        assert(result.isEmpty())
    }


}