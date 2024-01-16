package com.artsam.pavlo.presentation.screen.recipient

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface RecipientState : MviState {

    data object Uninitialized : RecipientState

    data class Content(
        val isChecked: Boolean = false,
    ) : RecipientState
}

sealed interface RecipientIntent : MviIntent {

    data class ChbChanged(val isChecked: Boolean) : RecipientIntent
}

sealed interface RecipientEffect : Effect {

}
