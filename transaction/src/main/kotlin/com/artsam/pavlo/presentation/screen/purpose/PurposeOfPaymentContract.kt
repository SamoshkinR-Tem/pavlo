package com.artsam.pavlo.presentation.screen.purpose

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface PurposeOfPaymentState : MviState {

    data object Uninitialized : PurposeOfPaymentState

    data class Content(
        val isChecked: Boolean = false,
    ) : PurposeOfPaymentState
}

sealed interface PurposeOfPaymentIntent : MviIntent {

    data class ChbChanged(val isChecked: Boolean) : PurposeOfPaymentIntent
}

sealed interface PurposeOfPaymentEffect : Effect {

}