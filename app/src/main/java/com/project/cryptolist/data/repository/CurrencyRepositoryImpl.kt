package com.project.cryptolist.data.repository

import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocal
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo

class CurrencyRepositoryImpl(private val dataSourceLocal: CurrencyDataSourceLocal) :
    CurrencyRepository {

    override suspend fun getCurrencyList(): List<CurrencyInfo> {
        return dataSourceLocal.getCurrencyList()
    }

    override suspend fun insertCurrencyList(currencyList: List<CurrencyInfo>) {
        dataSourceLocal.insertCurrencyList(currencyList)
    }

    override suspend fun deleteCurrencyList() {
        dataSourceLocal.deleteCurrencyList()
    }
}