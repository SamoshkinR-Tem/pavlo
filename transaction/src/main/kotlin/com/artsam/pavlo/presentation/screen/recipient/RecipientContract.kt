package com.artsam.pavlo.presentation.screen.recipient

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface RecipientState : MviState {

    data object Uninitialized : RecipientState

    data class Content(
        val isBtnNextEnabled: Boolean = false,
    ) : RecipientState
}

sealed interface RecipientIntent : MviIntent {

    data class ChbChanged(val isBtnNextEnabled: Boolean) : RecipientIntent
}

sealed interface RecipientEffect : Effect {

}
