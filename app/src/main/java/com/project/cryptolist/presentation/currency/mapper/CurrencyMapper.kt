package com.project.cryptolist.presentation.currency.mapper

import com.project.cryptolist.domain.model.CurrencyType

interface CurrencyMapper<From, To> {
    fun mapByCurrencyType(from: From, currencyType: CurrencyType): To
}