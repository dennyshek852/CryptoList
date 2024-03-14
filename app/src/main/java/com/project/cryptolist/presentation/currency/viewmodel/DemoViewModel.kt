package com.project.cryptolist.presentation.currency.viewmodel

import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.presentation.currency.model.ActionType

interface DemoViewModel {
    suspend fun onClick(actionType: ActionType)
    suspend fun getDisplayList(): List<CurrencyDisplayModel>
}