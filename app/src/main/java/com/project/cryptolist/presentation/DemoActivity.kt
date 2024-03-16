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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.cryptolist.domain.model.CurrencyType
import com.project.cryptolist.presentation.currency.model.ActionType
import com.project.cryptolist.presentation.currency.screen.CurrencyListScreen
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModel
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModel
import com.project.cryptolist.presentation.theme.CryptoListTheme
import com.project.cryptolist.presentation.theme.PaddingSmall
import com.test.cryptolist.R
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : ComponentActivity() {
    private val demoViewModel: DemoViewModel by viewModel<DemoViewModel>()
    private val currencyListViewModel: CurrencyListViewModel by viewModel<CurrencyListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoListTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.DemoScreen.route) {
                    composable(Screen.DemoScreen.route) {
                        DemoScreen(
                            navController = navController,
                            demoViewModel = demoViewModel,
                            currencyListViewModel = currencyListViewModel
                        )
                    }

                    composable(Screen.CurrencyListScreen.route) {
                        CurrencyListScreen(currencyListViewModel = currencyListViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun DemoScreen(
    navController: NavController,
    demoViewModel: DemoViewModel,
    currencyListViewModel: CurrencyListViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val onActionButtonClick: (ActionType) -> Unit = { actionType ->
        coroutineScope.launch {
            demoViewModel.onClick(actionType = actionType)
            if (actionType is ActionType.RouteToCurrencyList) {
                navController.navigate(route = Screen.CurrencyListScreen.route) {
                    launchSingleTop = true
                }
            }
        }
    }
    // you can collect the state with the `collectAsState` function and use it in your Compose UI.
    val displayList by demoViewModel.displayList.collectAsState()
    LaunchedEffect(displayList) {
        currencyListViewModel.setCurrencyList(currencyList = displayList)
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        ActionButton(
            labelRes = R.string.button_label_clear_data,
            toastRes = R.string.toast_message_clear_data
        ) {
            onActionButtonClick(ActionType.ClearData)
        }
        ActionButton(
            labelRes = R.string.button_label_insert_data,
            toastRes = R.string.toast_message_insert
        ) {
            onActionButtonClick(ActionType.InsertData)
        }
        ActionButton(labelRes = R.string.button_label_fiat_list) {
            onActionButtonClick(ActionType.RouteToCurrencyList(CurrencyType.Fiat))
        }
        ActionButton(labelRes = R.string.button_label_crypto_list) {
            onActionButtonClick(ActionType.RouteToCurrencyList(CurrencyType.Crypto))
        }
        ActionButton(labelRes = R.string.button_label_all_currency_list) {
            onActionButtonClick(ActionType.RouteToCurrencyList(CurrencyType.All))
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
        modifier = Modifier.padding(PaddingSmall),
        onClick = {
            onClick.invoke()
            toastMessage?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }
    ) {
        Text(text = stringResource(id = labelRes))
    }
}

