package com.artsam.pavlo.presentation.screen.transaction

import com.artsam.pavlo.presentation.core.MviViewModel
import com.artsam.pavlo.presentation.screen.transaction.TransactionIntent.ChbChanged

class TransactionViewModel() :
    MviViewModel<TransactionState, TransactionIntent, TransactionEffect>() {

    override val emptyState = TransactionState.Uninitialized

    init {
        setState(TransactionState.Content())
    }

    override fun handleIntent(intent: TransactionIntent) {
        when (intent) {
            is ChbChanged -> changeChb(intent.isChecked)
        }
    }

    private fun changeChb(isChecked: Boolean) = (currentState as? TransactionState.Content)
        ?.copy(isChecked = isChecked)
        ?.let(::setState)
}