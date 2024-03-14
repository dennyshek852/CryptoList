package com.project.cryptolist.data.datasource.local.currency

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}