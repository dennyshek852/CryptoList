package com.project.cryptolist

import com.project.cryptolist.data.datasource.local.currency.CurrencyInfo
import com.project.cryptolist.domain.model.CurrencyDisplayModel

class MockDataHelper {
    companion object {

        val mockFullDataList = listOf(
            //Crypto
            CurrencyInfo(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC"
            ),
            CurrencyInfo(
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH"
            ),
            CurrencyInfo(
                id = "XRP",
                name = "XRP",
                symbol = "XRP"
            ),
            CurrencyInfo(
                id = "BCH",
                name = "Bitcoin Cash",
                symbol = "BCH"
            ),
            CurrencyInfo(
                id = "LTC",
                name = "Litecoin",
                symbol = "LTC"
            ),
            CurrencyInfo(
                id = "EOS",
                name = "EOS",
                symbol = "EOS"
            ),
            CurrencyInfo(
                id = "BNB",
                name = "Binance Coin",
                symbol = "BNB"
            ),
            CurrencyInfo(
                id = "LINK",
                name = "Chainlink",
                symbol = "LINK"
            ),
            CurrencyInfo(
                id = "NEO",
                name = "NEO",
                symbol = "NEO"
            ),
            CurrencyInfo(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            ),
            CurrencyInfo(
                id = "ONT",
                name = "Ontology",
                symbol = "ONT"
            ),
            CurrencyInfo(
                id = "CRO",
                name = "Crypto.com Chain",
                symbol = "CRO"
            ),
            CurrencyInfo(
                id = "CUC",
                name = "Cucumber",
                symbol = "CUC"
            ),
            CurrencyInfo(
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC"
            ),
            CurrencyInfo(
                id = "TRXC",
                name = "TRONCLASSIC",
                symbol = "TRXC",
            ),
            CurrencyInfo(
                id = "BET",
                name = "BetCoin",
                symbol = "BET",
            ),
            CurrencyInfo(
                id = "FOO",
                name = "Foobar",
                symbol = "FOO",
            ),
            CurrencyInfo(
                id = "ETN",
                name = "Electroneum",
                symbol = "ETN"
            ),
            //Fiat
            CurrencyInfo(
                id = "SGD",
                name = "Singapore Dollar",
                symbol = "$",
                code = "SGD"
            ),
            CurrencyInfo(
                id = "EUR",
                name = "Euro",
                symbol = "€",
                code = "EUR"
            ),
            CurrencyInfo(
                id = "GBP",
                name = "British Pound",
                symbol = "£",
                code = "GBP"
            ),
            CurrencyInfo(
                id = "HKD",
                name = "Hong Kong Dollar",
                symbol = "$",
                code = "HKD"
            ),
            CurrencyInfo(
                id = "JPY",
                name = "Japanese Yen",
                symbol = "¥",
                code = "JPY"
            ),
            CurrencyInfo(
                id = "AUD",
                name = "Australian Dollar",
                symbol = "$",
                code = "AUD"
            ),
            CurrencyInfo(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
                code = "USD"
            )
        )

        val mockFullDisplayList = listOf(
            //Crypto
            CurrencyDisplayModel(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC"
            ),
            CurrencyDisplayModel(
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH"
            ),
            CurrencyDisplayModel(
                id = "XRP",
                name = "XRP",
                symbol = "XRP"
            ),
            CurrencyDisplayModel(
                id = "BCH",
                name = "Bitcoin Cash",
                symbol = "BCH"
            ),
            CurrencyDisplayModel(
                id = "LTC",
                name = "Litecoin",
                symbol = "LTC"
            ),
            CurrencyDisplayModel(
                id = "EOS",
                name = "EOS",
                symbol = "EOS"
            ),
            CurrencyDisplayModel(
                id = "BNB",
                name = "Binance Coin",
                symbol = "BNB"
            ),
            CurrencyDisplayModel(
                id = "LINK",
                name = "Chainlink",
                symbol = "LINK"
            ),
            CurrencyDisplayModel(
                id = "NEO",
                name = "NEO",
                symbol = "NEO"
            ),
            CurrencyDisplayModel(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            ),
            CurrencyDisplayModel(
                id = "ONT",
                name = "Ontology",
                symbol = "ONT"
            ),
            CurrencyDisplayModel(
                id = "CRO",
                name = "Crypto.com Chain",
                symbol = "CRO"
            ),
            CurrencyDisplayModel(
                id = "CUC",
                name = "Cucumber",
                symbol = "CUC"
            ),
            CurrencyDisplayModel(
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC"
            ),
            CurrencyDisplayModel(
                id = "TRXC",
                name = "TRONCLASSIC",
                symbol = "TRXC",
            ),
            CurrencyDisplayModel(
                id = "BET",
                name = "BetCoin",
                symbol = "BET",
            ),
            CurrencyDisplayModel(
                id = "FOO",
                name = "Foobar",
                symbol = "FOO",
            ),
            CurrencyDisplayModel(
                id = "ETN",
                name = "Electroneum",
                symbol = "ETN"
            ),
            //Fiat
            CurrencyDisplayModel(
                id = "SGD",
                name = "Singapore Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "EUR",
                name = "Euro",
                symbol = "€",
            ),
            CurrencyDisplayModel(
                id = "GBP",
                name = "British Pound",
                symbol = "£",
            ),
            CurrencyDisplayModel(
                id = "HKD",
                name = "Hong Kong Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "JPY",
                name = "Japanese Yen",
                symbol = "¥",
            ),
            CurrencyDisplayModel(
                id = "AUD",
                name = "Australian Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
            )
        )

        val mockCryptoDisplayList = listOf(
            //Crypto
            CurrencyDisplayModel(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC"
            ),
            CurrencyDisplayModel(
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH"
            ),
            CurrencyDisplayModel(
                id = "XRP",
                name = "XRP",
                symbol = "XRP"
            ),
            CurrencyDisplayModel(
                id = "BCH",
                name = "Bitcoin Cash",
                symbol = "BCH"
            ),
            CurrencyDisplayModel(
                id = "LTC",
                name = "Litecoin",
                symbol = "LTC"
            ),
            CurrencyDisplayModel(
                id = "EOS",
                name = "EOS",
                symbol = "EOS"
            ),
            CurrencyDisplayModel(
                id = "BNB",
                name = "Binance Coin",
                symbol = "BNB"
            ),
            CurrencyDisplayModel(
                id = "LINK",
                name = "Chainlink",
                symbol = "LINK"
            ),
            CurrencyDisplayModel(
                id = "NEO",
                name = "NEO",
                symbol = "NEO"
            ),
            CurrencyDisplayModel(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            ),
            CurrencyDisplayModel(
                id = "ONT",
                name = "Ontology",
                symbol = "ONT"
            ),
            CurrencyDisplayModel(
                id = "CRO",
                name = "Crypto.com Chain",
                symbol = "CRO"
            ),
            CurrencyDisplayModel(
                id = "CUC",
                name = "Cucumber",
                symbol = "CUC"
            ),
            CurrencyDisplayModel(
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC"
            ),
            CurrencyDisplayModel(
                id = "TRXC",
                name = "TRONCLASSIC",
                symbol = "TRXC",
            ),
            CurrencyDisplayModel(
                id = "BET",
                name = "BetCoin",
                symbol = "BET",
            ),
            CurrencyDisplayModel(
                id = "FOO",
                name = "Foobar",
                symbol = "FOO",
            ),
            CurrencyDisplayModel(
                id = "ETN",
                name = "Electroneum",
                symbol = "ETN"
            )
        )

        val mockFiatDisplayList = listOf(
            //Fiat
            CurrencyDisplayModel(
                id = "SGD",
                name = "Singapore Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "EUR",
                name = "Euro",
                symbol = "€",
            ),
            CurrencyDisplayModel(
                id = "GBP",
                name = "British Pound",
                symbol = "£",
            ),
            CurrencyDisplayModel(
                id = "HKD",
                name = "Hong Kong Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "JPY",
                name = "Japanese Yen",
                symbol = "¥",
            ),
            CurrencyDisplayModel(
                id = "AUD",
                name = "Australian Dollar",
                symbol = "$",
            ),
            CurrencyDisplayModel(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
            ),
        )

        val spacePrefixedData = listOf(
            CurrencyDisplayModel(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            )
        )

        val ethereumPrefixedData = listOf(
            CurrencyDisplayModel(
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH"
            ),
            CurrencyDisplayModel(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            )
        )

        val etPrefixedData = listOf(
            CurrencyDisplayModel(
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH"
            ),
            CurrencyDisplayModel(
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC"
            ),
            CurrencyDisplayModel(
                id = "ETN",
                name = "Electroneum",
                symbol = "ETN"
            )
        )

        val jpyPrefixedData = listOf(
            CurrencyDisplayModel(
                id = "JPY",
                name = "Japanese Yen",
                symbol = "¥"
            )
        )

        val coPrefixedData = listOf(
            CurrencyDisplayModel(
                id = "BNB",
                name = "Binance Coin",
                symbol = "BNB"
            ),
            CurrencyDisplayModel(
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC"
            )
        )

        val notMatchCoPrefixData = CurrencyDisplayModel(
            id = "CRO",
            name = "Crypto.com Chain",
            symbol = "CRO"
        )

    }
}