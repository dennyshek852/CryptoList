package com.project.cryptolist.di

import androidx.room.Room
import com.google.gson.Gson
import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocal
import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocalImpl
import com.project.cryptolist.data.datasource.local.currency.CurrencyDatabase
import com.project.cryptolist.data.repository.CurrencyRepository
import com.project.cryptolist.data.repository.CurrencyRepositoryImpl
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCaseImpl
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCaseImpl
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapper
import com.project.cryptolist.presentation.currency.mapper.CurrencyMapperImpl
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModelImpl
import com.project.cryptolist.presentation.currency.viewmodel.DemoViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModule = module {

    // Provide database instance
    single {
        Room.databaseBuilder(
            androidContext(),
            CurrencyDatabase::class.java,
            "CurrencyInfo"
        ).build()
    }

    // Provide dao instance
    single {
        get<CurrencyDatabase>().currencyDao()
    }

    // Provide datasource instance
    single<CurrencyDataSourceLocal> {
        CurrencyDataSourceLocalImpl(get())
    }

    // Provide repository instance
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(get())
    }
}

val domainModule = module {
    // Provide CurrencyUseCase instance
    single<CurrencyUseCase> {
        CurrencyUseCaseImpl(
            get()
        )
    }
    // Provide CurrencySearchUseCase instance
    single<CurrencySearchUseCase> {
        CurrencySearchUseCaseImpl()
    }
}

val presentationModule = module {
    // Provide CurrencyMapper instance
    single<CurrencyMapper> {
        CurrencyMapperImpl()
    }

    //Provide DemoViewModel instance
    viewModel {
        DemoViewModelImpl(get(), get())
    }

    //Provide CurrencyListViewModelImpl instance
    viewModel {
        CurrencyListViewModelImpl(get())
    }

}