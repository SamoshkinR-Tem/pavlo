package com.artsam.pavlo.di

import com.artsam.pavlo.data.repository.TransactionRepositoryImpl
import com.artsam.pavlo.domain.repository.TransactionRepository
import com.artsam.pavlo.domain.usecase.PostTransactionDataUseCase
import com.artsam.pavlo.domain.usecase.PostTransactionDataUseCaseImpl
import com.artsam.pavlo.presentation.screen.bank.IntermediaryBankViewModel
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationViewModel
import com.artsam.pavlo.presentation.screen.purpose.PurposeOfPaymentViewModel
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel
import com.artsam.pavlo.presentation.screen.transaction.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val transactionModule = module {
    viewModelOf(::TransactionViewModel)
    viewModelOf(::RecipientViewModel)
    viewModelOf(::IntermediaryBankViewModel)
    viewModelOf(::PurposeOfPaymentViewModel)
    viewModelOf(::ConfirmationViewModel)

    factoryOf(::PostTransactionDataUseCaseImpl) bind PostTransactionDataUseCase::class

    factoryOf(::TransactionRepositoryImpl) bind TransactionRepository::class
}
