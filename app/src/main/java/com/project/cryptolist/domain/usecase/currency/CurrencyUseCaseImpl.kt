package com.project.cryptolist.domain.usecase.currency

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.data.repository.CurrencyRepository

class CurrencyUseCaseImpl(
    private val currencyRepository: CurrencyRepository,
) : CurrencyUseCase {
    override suspend fun clearCurrencyList() {
        return currencyRepository.deleteCurrencyList()
    }

    override suspend fun insertCurrencyList(currencyInfoList: List<CurrencyInfo>) {
        return currencyRepository.insertCurrencyList(currencyInfoList)
    }

    override suspend fun getCurrencyList(): List<CurrencyInfo> {
        return currencyRepository.getCurrencyList()
    }

    override suspend fun getStubCurrencyList(jsonString: String): List<CurrencyInfo> {
        return runCatching<List<CurrencyInfo>> {
            val listType = object : TypeToken<List<CurrencyInfo>>() {}.type
            Gson().fromJson(jsonString, listType)
        }.getOrElse {
            emptyList()
        }
    }
}