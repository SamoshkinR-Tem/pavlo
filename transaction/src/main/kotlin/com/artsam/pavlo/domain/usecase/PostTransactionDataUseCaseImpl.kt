package com.artsam.pavlo.domain.usecase

import com.artsam.pavlo.domain.repository.TransactionRepository

class PostTransactionDataUseCaseImpl(
    private val repo: TransactionRepository,
) : PostTransactionDataUseCase {
    override suspend fun invoke(): Result<Unit> = repo.postTransactionData()
}