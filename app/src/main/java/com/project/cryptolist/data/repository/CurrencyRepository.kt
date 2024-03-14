package com.project.cryptolist.data.repository

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo

interface CurrencyRepository {
    suspend fun getCurrencyList(): List<CurrencyInfo>
    suspend fun insertCurrencyList(currencyList: List<CurrencyInfo>)
    suspend fun deleteCurrencyList()
}