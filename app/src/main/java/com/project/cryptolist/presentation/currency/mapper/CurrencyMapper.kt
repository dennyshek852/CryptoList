package com.project.cryptolist.presentation.currency.mapper

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.model.CurrencyType

interface CurrencyMapper {
    fun getCurrencyDisplayListByType(
        currencyList: List<CurrencyInfo>,
        currencyType: CurrencyType
    ): List<CurrencyDisplayModel>
}