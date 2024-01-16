package com.artsam.pavlo.di

import com.artsam.pavlo.presentation.screen.bank.IntermediaryBankViewModel
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationViewModel
import com.artsam.pavlo.presentation.screen.purpose.PurposeOfPaymentViewModel
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel
import com.artsam.pavlo.presentation.screen.transaction.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val transactionModule = module {
    viewModelOf(::TransactionViewModel)
    viewModelOf(::RecipientViewModel)
    viewModelOf(::IntermediaryBankViewModel)
    viewModelOf(::PurposeOfPaymentViewModel)
    viewModelOf(::ConfirmationViewModel)
}
