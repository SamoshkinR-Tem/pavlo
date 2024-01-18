package com.artsam.pavlo.domain.repository

interface TransactionRepository {
    suspend fun postTransactionData(): Result<Unit>
}