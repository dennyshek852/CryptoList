package com.project.cryptolist.di.module

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapper
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapperImpl
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModel
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    // Provide CurrencyMapper instance
    single<CurrencyMapper<List<CurrencyInfo>, List<CurrencyDisplayModel>>> {
        CurrencyMapperImpl()
    }

    //Provide DemoViewModel instance
    viewModel {
        DemoViewModel(get(), get())
    }

    //Provide CurrencyListViewModelImpl instance
    viewModel { CurrencyListViewModel(get<CurrencySearchUseCase>()) }


}