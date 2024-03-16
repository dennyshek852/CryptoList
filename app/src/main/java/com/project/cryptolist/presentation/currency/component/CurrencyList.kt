package com.project.cryptolist.presentation.currency.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.test.cryptolist.R


@Composable
fun CurrencyList(displayList: List<CurrencyDisplayModel>) {
    if (displayList.isEmpty()) {
        EmptyList(
            imageRes = R.drawable.search_fail,
            title = stringResource(R.string.empty_list_title),
            subtitle = stringResource(id = R.string.empty_crypto_list_subtitle)
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(displayList.size) { index ->
                CurrencyItem(currencyDisplayModel = displayList[index])
            }
        }
    }
}
