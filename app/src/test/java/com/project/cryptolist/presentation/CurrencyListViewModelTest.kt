package com.project.cryptolist.presentation

import com.project.cryptolist.MockDataHelper.Companion.etPrefixedData
import com.project.cryptolist.MockDataHelper.Companion.mockFullDisplayList
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModel
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModelImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyListViewModelTest {

    //Mock currency search use case
    private lateinit var mockSearchUseCase: CurrencySearchUseCase

    //The class we are going to test
    private lateinit var currencyListViewModel: CurrencyListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        mockSearchUseCase = mockk<CurrencySearchUseCase>()
        currencyListViewModel = CurrencyListViewModelImpl(mockSearchUseCase)
    }

    @Test
    fun test_setCurrencyList_return_valid_list() = runTest(StandardTestDispatcher()) {
        //Given
        launch { currencyListViewModel.setCurrencyList(mockFullDisplayList) }
        //When
        launch { currencyListViewModel.cleanSearch() }
        //Then
        advanceUntilIdle()
        val result = currencyListViewModel.getDisplayList()
        assert(result == mockFullDisplayList)

    }

    @Test
    fun test_setCurrencyList_return_empty_list() = runTest(StandardTestDispatcher()) {
        //Given
        //When
        launch { currencyListViewModel.setCurrencyList(emptyList()) }
        //Then
        advanceUntilIdle()
        val result = currencyListViewModel.getDisplayList()
        assert(result == emptyList<CurrencyDisplayModel>())
    }

    @Test
    fun test_search_returns_search_result() = runTest(StandardTestDispatcher()) {
        //Given
        val keywords = "et"
        coEvery { mockSearchUseCase.search(any(), any()) } returns etPrefixedData
        //When
        launch { currencyListViewModel.search(keywords = keywords) }
        //Then
        advanceUntilIdle()
        coVerify {
            mockSearchUseCase.search(
                any(),
                any()
            )
        }
        launch {
            val result = currencyListViewModel.getDisplayList()
            assert(result == etPrefixedData)
        }
    }

    @Test
    fun test_cleanSearch_returns_full_list() = runTest(StandardTestDispatcher()) {
        //Given
        launch { currencyListViewModel.setCurrencyList(mockFullDisplayList) }
        val keywords = "et"
        coEvery { mockSearchUseCase.search(any(), any()) } returns etPrefixedData
        //When
        launch { currencyListViewModel.search(keywords = keywords) }
        launch { currencyListViewModel.cleanSearch() }
        //Then
        advanceUntilIdle()
        val result = currencyListViewModel.getDisplayList()
        assert(result == mockFullDisplayList)
    }
}