package com.artsam.pavlo.presentation.screen.bank

import com.artsam.pavlo.presentation.core.MviViewModel

class IntermediaryBankViewModel :
    MviViewModel<IntermediaryBankState, IntermediaryBankIntent, IntermediaryBankEffect>() {

    override val emptyState = IntermediaryBankState.Uninitialized

    init {
        setState(IntermediaryBankState.Content())
    }

    override fun handleIntent(intent: IntermediaryBankIntent) {
        when (intent) {
            is IntermediaryBankIntent.ChbChanged -> changeChb(intent.isBtnNextEnabled)
        }
    }

    private fun changeChb(isChecked: Boolean) = (currentState as? IntermediaryBankState.Content)
        ?.copy(isBtnNextEnabled = isChecked)
        ?.let(::setState)
}
