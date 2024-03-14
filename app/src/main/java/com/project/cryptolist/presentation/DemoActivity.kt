package com.project.cryptolist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.cryptolist.domain.model.CurrencyType
import com.project.cryptolist.presentation.currency.model.ActionType
import com.project.cryptolist.presentation.screen.CurrencyListScreen
import com.project.cryptolist.presentation.theme.CryptoListTheme
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModelImpl
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModelImpl
import com.project.cryptolist.presentation.screen.Screen
import com.test.cryptolist.R
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : ComponentActivity() {
    private val demoViewModel: DemoViewModelImpl by viewModel<DemoViewModelImpl>()
    private val currencyListViewModel: CurrencyListViewModelImpl by viewModel<CurrencyListViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoListTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = Screen.DemoActivity.route) {
                    composable(Screen.DemoActivity.route) {
                        DemoScreen(navController, demoViewModel, currencyListViewModel)
                    }

                    composable(Screen.CurrencyListFragment.route) {
                        CurrencyListScreen(currencyListViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun DemoScreen(
    navController: NavController,
    demoViewModel: DemoViewModelImpl,
    currencyListViewModel: CurrencyListViewModelImpl
) {
    val coroutineScope = rememberCoroutineScope()
    // you can collect the state with the `collectAsState` function and use it in your Compose UI.
    val displayList by demoViewModel.displayList.collectAsState()
    LaunchedEffect(displayList) {
        currencyListViewModel.setCurrencyList(displayList)
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        ActionButton(
            R.string.button_label_clear_data,
            R.string.button_label_clear_data
        ) {
            coroutineScope.launch {
                demoViewModel.onClick(actionType = ActionType.ClearData)
            }
        }
        ActionButton(
            R.string.button_label_insert_data,
            R.string.button_label_insert_data
        ) {
            coroutineScope.launch {
                demoViewModel.onClick(actionType = ActionType.InsertData)
            }
        }
        ActionButton(R.string.button_label_fiat_list) {
            coroutineScope.launch {
                demoViewModel.onClick(actionType = ActionType.SwitchCurrency(CurrencyType.Fiat))
                navController.navigate(Screen.CurrencyListFragment.route)
            }
        }
        ActionButton(R.string.button_label_crypto_list) {
            coroutineScope.launch {
                demoViewModel.onClick(actionType = ActionType.SwitchCurrency(CurrencyType.Crypto))
                navController.navigate(Screen.CurrencyListFragment.route)
            }
        }
        ActionButton(R.string.button_label_all_currency_list) {
            coroutineScope.launch {
                demoViewModel.onClick(actionType = ActionType.SwitchCurrency(CurrencyType.All))
                navController.navigate(Screen.CurrencyListFragment.route)
            }
        }
    }
}

@Composable
fun ActionButton(
    labelRes: Int,
    toastRes: Int? = null,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val toastMessage = toastRes?.let { stringResource(id = it) }
    Button(
        modifier = Modifier.padding(5.dp),
        onClick = {
            onClick.invoke()
            toastMessage?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }
    ) {
        Text(stringResource(id = labelRes))
    }
}

