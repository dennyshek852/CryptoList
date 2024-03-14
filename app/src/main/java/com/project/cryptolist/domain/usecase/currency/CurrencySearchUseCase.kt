package com.project.cryptolist.domain.usecase.currency

import com.project.cryptolist.domain.model.CurrencyDisplayModel

interface CurrencySearchUseCase {
    suspend fun search(
        currencyList: List<CurrencyDisplayModel>,
        keywords: String
    ): List<CurrencyDisplayModel>
}