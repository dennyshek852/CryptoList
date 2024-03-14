package com.project.cryptolist.data.datasource.local.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM CurrencyInfo")
    suspend fun getCurrencyList(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyList(currencyList: List<CurrencyInfo>)

    @Query("DELETE FROM CurrencyInfo")
    suspend fun deleteCurrencyList()

}
