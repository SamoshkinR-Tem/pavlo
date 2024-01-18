package com.artsam.pavlo.presentation.screen.purpose

import com.artsam.pavlo.presentation.core.MviViewModel

class PurposeOfPaymentViewModel() :
    MviViewModel<PurposeOfPaymentState, PurposeOfPaymentIntent, PurposeOfPaymentEffect>() {

    override val emptyState = PurposeOfPaymentState.Uninitialized

    init {
        setState(PurposeOfPaymentState.Content())
    }

    override fun handleIntent(intent: PurposeOfPaymentIntent) {
        when (intent) {
            is PurposeOfPaymentIntent.ChbChanged -> changeChb(intent.isBtnNextEnabled)
        }
    }

    private fun changeChb(isChecked: Boolean) = (currentState as? PurposeOfPaymentState.Content)
        ?.copy(isBtnNextEnabled = isChecked)
        ?.let(::setState)
}
