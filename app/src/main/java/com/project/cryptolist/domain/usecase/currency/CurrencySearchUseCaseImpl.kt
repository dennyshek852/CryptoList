package com.project.cryptolist.domain.usecase.currency

import com.project.cryptolist.domain.model.CurrencyDisplayModel

class CurrencySearchUseCaseImpl : CurrencySearchUseCase {

    override suspend fun search(
        currencyList: List<CurrencyDisplayModel>,
        keywords: String
    ): List<CurrencyDisplayModel> {
        return currencyList.filter {
            when {
                keywords.isEmpty() -> {
                    //Empty no filter needed
                    true
                }

                keywords.first().isLetterOrDigit() -> {
                    //Escape prevent PatternSyntaxException
                    val escapeKeyword = Regex.escape(keywords)
                    //regex for non special char
                    val regexPattern = """(?<!\.)\b$escapeKeyword\w*""".toRegex(RegexOption.IGNORE_CASE)
                    regexPattern.containsMatchIn(it.id)
                            || regexPattern.containsMatchIn(it.name)
                            || regexPattern.containsMatchIn(it.symbol)
                }

                else -> {
                    //normal check for special char
                    keywords == it.symbol
                }
            }
        }
    }

}