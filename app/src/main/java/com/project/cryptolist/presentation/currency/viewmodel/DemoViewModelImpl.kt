package com.project.cryptolist.presentation.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.data.stub.StubHelper
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.model.CurrencyType
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCase
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapper
import com.project.cryptolist.presentation.currency.model.ActionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModelImpl(
    private val currencyUseCase: CurrencyUseCase,
    private val currencyMapper: CurrencyMapper
) : DemoViewModel, ViewModel() {

    private val _displayList = MutableStateFlow<List<CurrencyDisplayModel>>(emptyList())
    val displayList: StateFlow<List<CurrencyDisplayModel>> = _displayList

    override suspend fun onClick(actionType: ActionType) {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                when (actionType) {
                    ActionType.ClearData -> {
                        currencyUseCase.clearCurrencyList()
                        emptyList()
                    }

                    ActionType.InsertData -> {
                        //Stub data
                        currencyUseCase.insertCurrencyList(currencyUseCase.getStubCurrencyList())
                        currencyMapper.getCurrencyDisplayListByType(
                            currencyUseCase.getCurrencyList(),
                            CurrencyType.All
                        )
                    }

                    is ActionType.SwitchCurrency -> {
                        currencyMapper.getCurrencyDisplayListByType(
                            currencyUseCase.getCurrencyList(),
                            actionType.currencyType
                        )
                    }
                }
            }
            _displayList.emit(list)
        }
    }

    override suspend fun getDisplayList(): List<CurrencyDisplayModel> {
        return displayList.value
    }

}