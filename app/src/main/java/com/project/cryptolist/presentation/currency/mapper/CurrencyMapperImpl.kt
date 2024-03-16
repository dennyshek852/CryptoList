package com.project.cryptolist.presentation.currency.mapper

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.model.CurrencyType

/**
 * Filters and transforms a list of CurrencyInfo objects into a list of CurrencyDisplayModel,
 * based on the given CurrencyType.
 *
 * @param currencyList The source list of CurrencyInfo objects.
 * @param currencyType The target filter type for the list.
 * @return List of CurrencyDisplayModel
 */
class CurrencyMapperImpl : CurrencyMapper<List<CurrencyInfo>, List<CurrencyDisplayModel>> {
    override fun mapByCurrencyType(
        from: List<CurrencyInfo>,
        currencyType: CurrencyType
    ): List<CurrencyDisplayModel> {
        return when (currencyType) {
            CurrencyType.All -> {
                from
            }

            CurrencyType.Crypto -> {
                from.filter { it.code == null }
            }

            CurrencyType.Fiat -> {
                from.filter { it.code != null }
            }
        }.map {
            CurrencyDisplayModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol
            )
        }
    }
}