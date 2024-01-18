package com.artsam.pavlo.domain.model

data class IntermediaryBankData(
    val isEnabled: Boolean = false,
    val swiftCode: String? = null,
    val anotherSwiftCode: String? = null,
    val recipientBankAccount: String? = null,
    val intermediaryBankAccountInRecipientBank: String? = null,
    val title: String? = null,
    val country: String? = null,
    val address: String? = null,
)
