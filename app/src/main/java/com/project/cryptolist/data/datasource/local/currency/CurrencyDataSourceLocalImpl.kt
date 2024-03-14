package com.project.cryptolist.data.datasource.local.currency

class CurrencyDataSourceLocalImpl(
    private val currencyDao: CurrencyDao,
) : CurrencyDataSourceLocal {

    override suspend fun getCurrencyList(): List<CurrencyInfo> {
        return currencyDao.getCurrencyList()
    }

    override suspend fun insertCurrencyList(currencyList: List<CurrencyInfo>) {
        return currencyDao.insertCurrencyList(currencyList)
    }

    override suspend fun deleteCurrencyList() {
        return currencyDao.deleteCurrencyList()
    }
}