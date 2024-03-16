package com.project.cryptolist.domain

import com.project.cryptolist.MockDataHelper.Companion.etPrefixedData
import com.project.cryptolist.MockDataHelper.Companion.ethereumPrefixedData
import com.project.cryptolist.MockDataHelper.Companion.jpyPrefixedData
import com.project.cryptolist.MockDataHelper.Companion.mockFullDisplayList
import com.project.cryptolist.MockDataHelper.Companion.spacePrefixedData
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencySearchUseCaseTest {

    //The class we are going to test
    private lateinit var currencySearchUseCase: CurrencySearchUseCase

    @Before
    fun setup() {
        currencySearchUseCase = CurrencySearchUseCaseImpl()
    }

    @Test
    fun test_search_name_contains_characters_mismatch_prefix_return_empty_list() {
        runTest(StandardTestDispatcher()) {
            //Given keyword mismatch "Android"
            val keyword = "eum"
            val mockData = mockFullDisplayList
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == listOf<CurrencyDisplayModel>())
        }
    }

    @Test
    fun test_search_name_match_prefix_returns_currency_list() =
        runTest(StandardTestDispatcher()) {
            //Given
            val keyword = "Ethereum"
            val mockData = mockFullDisplayList
            val expectedResult = ethereumPrefixedData
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == expectedResult)
        }

    @Test
    fun test_search_name_partial_match_with_space_prefix_return_currency_list() =
        runTest(StandardTestDispatcher()) {
            //Given
            val keyword = "Classic"
            val mockData = mockFullDisplayList
            val expectedResult = spacePrefixedData
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == expectedResult)
        }

    @Test
    fun test_search_symbols_match_prefix_returns_currency_list() =
        runTest(StandardTestDispatcher()) {
            //Given
            val keyword = "ET"
            val mockData = mockFullDisplayList
            val expectedResult = etPrefixedData
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == expectedResult)
        }

    @Test
    fun test_search_symbols_jpy_match_prefix_returns_currency_list() =
        runTest(StandardTestDispatcher()) {
            //Given
            val keyword = "¥"
            val mockData = mockFullDisplayList
            val expectedResult = jpyPrefixedData
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == expectedResult)
        }


    @Test
    fun test_search_characters_mismatch_prefix_return_empty_list() =
        runTest(StandardTestDispatcher()) {
            //Given
            val keyword = "Android"
            val mockData = mockFullDisplayList
            val expectedResult = listOf<CurrencyDisplayModel>()
            //When search
            val result = currencySearchUseCase.search(mockData, keyword)
            //Then verify expected empty list
            assert(result == listOf<CurrencyDisplayModel>())
        }


}