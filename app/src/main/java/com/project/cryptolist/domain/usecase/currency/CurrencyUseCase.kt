package com.project.cryptolist.domain.usecase.currency

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo

interface CurrencyUseCase {
    suspend fun clearCurrencyList()
    suspend fun insertCurrencyList(currencyInfoList: List<CurrencyInfo>)
    suspend fun getCurrencyList(): List<CurrencyInfo>
    suspend fun getStubCurrencyList(jsonString: String): List<CurrencyInfo>
}