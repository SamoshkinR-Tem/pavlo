package com.artsam.pavlo.domain.model

data class TransactionModel(
    val transactionSum: Int,
    val commission: String,
    val commissionAccount: String,
    val transactionNumber: Int,
    val bankDepartment: String,
    val recipientData: RecipientData,
    val intermediaryBankData: IntermediaryBankData,
    val purposeOfPayment: PurposeOfPayment,
)