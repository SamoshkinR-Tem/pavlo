package com.artsam.pavlo.data.repository

import com.artsam.pavlo.domain.repository.TransactionRepository
import kotlinx.coroutines.delay

class TransactionRepositoryImpl : TransactionRepository {

    override suspend fun postTransactionData(): Result<Unit> {
        delay(3000)
        return Result.success(Unit)
    }
}
