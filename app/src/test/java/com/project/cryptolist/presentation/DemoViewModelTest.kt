package com.project.cryptolist.presentation

import com.project.cryptolist.MockDataHelper.Companion.mockCryptoDisplayList
import com.project.cryptolist.MockDataHelper.Companion.mockFiatDisplayList
import com.project.cryptolist.MockDataHelper.Companion.mockFullDataList
import com.project.cryptolist.MockDataHelper.Companion.mockFullDisplayList
import com.project.cryptolist.domain.model.CurrencyType
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCase
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapper
import com.project.cryptolist.presentation.currency.model.ActionType
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModel
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModelImpl
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DemoViewModelTest {

    //Mock currency search use case
    private lateinit var mockCurrencyUseCase: CurrencyUseCase

    private lateinit var mockCurrencyMapper: CurrencyMapper

    //The class we are going to test
    private lateinit var demoViewModel: DemoViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        mockCurrencyUseCase = mockk<CurrencyUseCase>()
        mockCurrencyMapper = mockk<CurrencyMapper>()
        demoViewModel = DemoViewModelImpl(mockCurrencyUseCase, mockCurrencyMapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun test_onClick_clear_data() = runTest(StandardTestDispatcher()) {
        //Given
        coEvery { mockCurrencyUseCase.clearCurrencyList() } just Runs
        val actionType = ActionType.ClearData
        //When
        launch { demoViewModel.onClick(actionType) }
        //Then
        advanceUntilIdle()
        coVerify { mockCurrencyUseCase.clearCurrencyList() }
    }

    @Test
    fun test_onClick_insert_data() = runTest {
        // This will use runTest's dispatcher
        // Given
        coEvery { mockCurrencyUseCase.getCurrencyList() } returns mockFullDataList
        coEvery { mockCurrencyUseCase.getStubCurrencyList() } returns mockFullDataList
        coEvery {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.All
            )
        } returns mockFullDisplayList
        coEvery { mockCurrencyUseCase.insertCurrencyList(mockFullDataList) } just Runs
        // When
        demoViewModel.onClick(ActionType.InsertData)
        // Then
        advanceUntilIdle() // This will use runTest's advanceUntilIdle()
        coVerify { mockCurrencyUseCase.getCurrencyList() }
        coVerify { mockCurrencyUseCase.getStubCurrencyList() }
        coVerify {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.All
            )
        }
        coVerify { mockCurrencyUseCase.insertCurrencyList(mockFullDataList) }
        launch {
            val result = demoViewModel.getDisplayList()
            assert(result == mockFullDisplayList)
        }
    }

    @Test
    fun test_onClick_switch_currency_fiat() = runTest(StandardTestDispatcher()) {
        //Given
        coEvery { mockCurrencyUseCase.getCurrencyList() } returns mockFullDataList
        coEvery {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.Fiat
            )
        } returns mockFiatDisplayList
        val actionType = ActionType.SwitchCurrency(CurrencyType.Fiat)
        //When
        launch { demoViewModel.onClick(actionType) }
        //Then
        advanceUntilIdle()
        coVerify { mockCurrencyUseCase.getCurrencyList() }
        coVerify {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.Fiat
            )
        }
        launch {
            val result = demoViewModel.getDisplayList()
            assert(result == mockFiatDisplayList)
        }
    }

    @Test
    fun test_onClick_switch_currency_crypto() = runTest(StandardTestDispatcher()) {
        //Given
        coEvery { mockCurrencyUseCase.getCurrencyList() } returns mockFullDataList
        coEvery {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.Crypto
            )
        } returns mockCryptoDisplayList
        val actionType = ActionType.SwitchCurrency(CurrencyType.Crypto)
        //When
        launch { demoViewModel.onClick(actionType) }
        //Then
        advanceUntilIdle()
        coVerify { mockCurrencyUseCase.getCurrencyList() }
        coVerify {
            mockCurrencyMapper.getCurrencyDisplayListByType(
                mockFullDataList,
                CurrencyType.Crypto
            )
        }
        launch {
            val result = demoViewModel.getDisplayList()
            assert(result == mockCryptoDisplayList)
        }
    }

}