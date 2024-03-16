package com.project.cryptolist.di.module

import androidx.room.Room
import com.project.cryptolist.data.datasource.local.currency.CurrencyDao
import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocal
import com.project.cryptolist.data.datasource.local.currency.CurrencyDataSourceLocalImpl
import com.project.cryptolist.data.datasource.local.currency.CurrencyDatabase
import com.project.cryptolist.data.repository.CurrencyRepository
import com.project.cryptolist.data.repository.CurrencyRepositoryImpl
import org.koin.android.ext.koin.androidContext
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
        CurrencyDataSourceLocalImpl(currencyDao = get<CurrencyDao>())
    }

    // Provide repository instance
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(dataSourceLocal = get<CurrencyDataSourceLocal>())
    }
}