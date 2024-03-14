package com.project.cryptolist.domain.model

sealed class CurrencyType{
    data object Fiat : CurrencyType()
    data object Crypto : CurrencyType()
    data object All : CurrencyType()
}