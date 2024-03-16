package com.project.cryptolist.di.module

import com.project.cryptolist.data.repository.CurrencyRepository
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencySearchUseCaseImpl
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCase
import com.project.cryptolist.domain.usecase.currency.CurrencyUseCaseImpl
import org.koin.dsl.module


val domainModule = module {
    // Provide CurrencyUseCase instance
    single<CurrencyUseCase> { CurrencyUseCaseImpl(get<CurrencyRepository>()) }
    // Provide CurrencySearchUseCase instance
    single<CurrencySearchUseCase> { CurrencySearchUseCaseImpl() }
}
