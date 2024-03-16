package com.project.cryptolist.data.datasource.local.currency

interface CurrencyDataSourceLocal {
    suspend fun getCurrencyList(): List<CurrencyInfo>
    suspend fun insertCurrencyList(currencyList: List<CurrencyInfo>)
    suspend fun deleteCurrencyList()
}