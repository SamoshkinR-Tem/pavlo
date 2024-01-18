package com.artsam.pavlo.presentation.screen.purpose

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface PurposeOfPaymentState : MviState {

    data object Uninitialized : PurposeOfPaymentState

    data class Content(
        val isBtnNextEnabled: Boolean = false,
    ) : PurposeOfPaymentState
}

sealed interface PurposeOfPaymentIntent : MviIntent {

    data class ChbChanged(val isBtnNextEnabled: Boolean) : PurposeOfPaymentIntent
}

sealed interface PurposeOfPaymentEffect : Effect {

}