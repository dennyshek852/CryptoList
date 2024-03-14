package com.project.cryptolist.presentation.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListViewModelImpl(
    private val searchUseCase: CurrencySearchUseCase
) :
    CurrencyListViewModel, ViewModel() {

    private val _currencyList = MutableStateFlow<List<CurrencyDisplayModel>>(emptyList())

    private val _displayList = MutableStateFlow<List<CurrencyDisplayModel>>(emptyList())
    val displayList: StateFlow<List<CurrencyDisplayModel>> = _displayList

    override suspend fun search(keywords: String) {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                searchUseCase.search(_currencyList.value, keywords)
            }
            _displayList.emit(list)
        }
    }

    override suspend fun cleanSearch() {
        viewModelScope.launch {
            _displayList.emit(_currencyList.value)
        }
    }

    override suspend fun setCurrencyList(currencyList: List<CurrencyDisplayModel>) {
        viewModelScope.launch {
            _currencyList.emit(currencyList)
            _displayList.emit(currencyList)
        }
    }

    override suspend fun getDisplayList(): List<CurrencyDisplayModel> {
        return displayList.value
    }

}