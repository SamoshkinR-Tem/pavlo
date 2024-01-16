package com.artsam.pavlo.presentation.screen.confirmation

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface ConfirmationState : MviState {

    data object Uninitialized : ConfirmationState

    data class Content(
        val isChecked: Boolean = false,
    ) : ConfirmationState
}

sealed interface ConfirmationIntent : MviIntent {

    data class ChbChanged(val isChecked: Boolean) : ConfirmationIntent
}

sealed interface ConfirmationEffect : Effect {

}
