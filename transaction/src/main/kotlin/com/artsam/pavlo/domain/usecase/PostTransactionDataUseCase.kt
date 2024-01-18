package com.artsam.pavlo.domain.usecase

interface PostTransactionDataUseCase {
    suspend operator fun invoke(): Result<Unit>
}