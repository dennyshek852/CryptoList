package com.project.cryptolist.presentation.currency.model

import com.project.cryptolist.domain.model.CurrencyType

sealed class ActionType {
    data object ClearData : ActionType()
    data object InsertData : ActionType()
    data class RouteToCurrencyList(val currencyType: CurrencyType) : ActionType()
}