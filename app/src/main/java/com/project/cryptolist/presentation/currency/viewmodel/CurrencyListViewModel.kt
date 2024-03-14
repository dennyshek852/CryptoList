package com.project.cryptolist.presentation.currency.viewmodel

import com.project.cryptolist.domain.model.CurrencyDisplayModel

interface CurrencyListViewModel {
    suspend fun search(keywords: String)
    suspend fun cleanSearch()
    suspend fun setCurrencyList(currencyList: List<CurrencyDisplayModel>)
    suspend fun getDisplayList(): List<CurrencyDisplayModel>
}