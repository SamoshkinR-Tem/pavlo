package com.artsam.pavlo.presentation.screen.confirmation

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface ConfirmationState : MviState {

    data object Uninitialized : ConfirmationState

    data class Content(
        val isLoading: Boolean = false,
        val isShowToast: Boolean = false,
    ) : ConfirmationState
}

sealed interface ConfirmationIntent : MviIntent {
    data object PostTransactionData : ConfirmationIntent
    data object ToastShowed : ConfirmationIntent
}

sealed interface ConfirmationEffect : Effect {}
