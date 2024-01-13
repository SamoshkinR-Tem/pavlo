package com.artsam.pavlo.di

import com.artsam.pavlo.presentation.screen.transaction.TransactionViewModel
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val transactionModule = module {
    viewModelOf(::TransactionViewModel)
    viewModelOf(::RecipientViewModel)
}
