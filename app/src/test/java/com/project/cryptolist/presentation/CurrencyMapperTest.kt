package com.project.cryptolist.presentation

import com.project.cryptolist.MockDataHelper.Companion.mockCryptoDisplayList
import com.project.cryptolist.MockDataHelper.Companion.mockFiatDisplayList
import com.project.cryptolist.MockDataHelper.Companion.mockFullDataList
import com.project.cryptolist.MockDataHelper.Companion.mockFullDisplayList
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.model.CurrencyType
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapper
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapperImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CurrencyMapperTest {

    //The class we are going to test
    private lateinit var currencyMapper: CurrencyMapper<List<CurrencyInfo>, List<CurrencyDisplayModel>>

    @Before
    fun setup() {
        currencyMapper = CurrencyMapperImpl()
    }

    @Test
    fun test_getCurrencyDisplayListByType_returns_fiat_list() = runTest(StandardTestDispatcher()) {
        // Given mockData adn currency type is fiat
        // When calling the function on our class.
        val result = currencyMapper.mapByCurrencyType(
            mockFullDataList,
            CurrencyType.Fiat
        )
        // And the result is what our DAO function stub returned.
        assert(result == mockFiatDisplayList)
    }

    @Test
    fun test_getCurrencyDisplayListByType_returns_crypto_list() =
        runTest(StandardTestDispatcher()) {
            // When calling the function on our class.
            val result = currencyMapper.mapByCurrencyType(
                mockFullDataList,
                CurrencyType.Crypto
            )
            // And the result is what our DAO function stub returned.
            assert(result == mockCryptoDisplayList)
        }

    @Test
    fun test_getCurrencyDisplayListByType_returns_all_list() =
        runTest(StandardTestDispatcher()) {
            // When calling the function on our class.
            val result = currencyMapper.mapByCurrencyType(
                mockFullDataList,
                CurrencyType.All
            )
            // And the result is what our DAO function stub returned.
            assert(result == mockFullDisplayList)
        }
}