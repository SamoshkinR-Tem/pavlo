package com.artsam.pavlo.presentation.screen.confirmation

import com.artsam.pavlo.presentation.core.MviViewModel

class ConfirmationViewModel() :
    MviViewModel<ConfirmationState, ConfirmationIntent, ConfirmationEffect>() {

    override val emptyState = ConfirmationState.Uninitialized

    init {
        setState(ConfirmationState.Content())
    }

    override fun handleIntent(intent: ConfirmationIntent) {
        when (intent) {
            is ConfirmationIntent.ChbChanged -> changeChb(intent.isChecked)
        }
    }

    private fun changeChb(isChecked: Boolean) = (currentState as? ConfirmationState.Content)
        ?.copy(isChecked = isChecked)
        ?.let(::setState)
}
