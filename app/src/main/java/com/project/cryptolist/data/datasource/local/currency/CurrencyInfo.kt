package com.project.cryptolist.data.datasource.local.currency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * This is an Entity for currency database
 * @see com.project.cryptolist.data.datasource.local.currency.CurrencyDatabase
 * @param id The db primary key from server
 * @param name The display name of the currency
 * @param symbol The display symbol
 * @param code Fiat currency code, ISO 4217
 */

@Entity
data class CurrencyInfo(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("name") @ColumnInfo(name = "name") val name: String,
    @SerializedName("symbol") @ColumnInfo(name = "symbol") val symbol: String,
    @SerializedName("code") @ColumnInfo(name = "code") val code: String? = null
)