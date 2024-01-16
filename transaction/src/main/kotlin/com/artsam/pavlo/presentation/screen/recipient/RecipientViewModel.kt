package com.artsam.pavlo.presentation.screen.recipient

import com.artsam.pavlo.presentation.core.MviViewModel

class RecipientViewModel :
    MviViewModel<RecipientState, RecipientIntent, RecipientEffect>() {

    override val emptyState = RecipientState.Uninitialized

    init {
        setState(RecipientState.Content())
    }

    override fun handleIntent(intent: RecipientIntent) {
        when (intent) {
            is RecipientIntent.ChbChanged -> changeChb(intent.isChecked)
        }
    }

    private fun changeChb(isChecked: Boolean) = (currentState as? RecipientState.Content)
        ?.copy(isChecked = isChecked)
        ?.let(::setState)
}
