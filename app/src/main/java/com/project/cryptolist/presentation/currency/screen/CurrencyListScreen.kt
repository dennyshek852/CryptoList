package com.project.cryptolist.presentation.currency.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.project.cryptolist.presentation.currency.component.CurrencyList
import com.project.cryptolist.presentation.currency.component.SearchField
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModel
import com.project.cryptolist.presentation.theme.DividerHeight

@Composable
fun CurrencyListScreen(
    currencyListViewModel: CurrencyListViewModel
) {
    // you can collect the state with the `collectAsState` function and use it in your Compose UI.
    val displayList by currencyListViewModel.displayList.collectAsState()
    Column {
        //Search field
        SearchField(currencyListViewModel)
        //Divider
        Divider(thickness = DividerHeight, color = Color.LightGray)
        //Currency List
        CurrencyList(displayList = displayList)
    }
}