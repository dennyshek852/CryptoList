package com.project.cryptolist.presentation.screen

sealed class Screen(val route: String) {
    data object DemoActivity : Screen("demo_activity")
    data object CurrencyListFragment : Screen("currency_list_fragment")
}