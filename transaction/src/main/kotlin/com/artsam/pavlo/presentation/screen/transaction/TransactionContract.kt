package com.artsam.pavlo.presentation.screen.transaction

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface TransactionState : MviState {

    data object Uninitialized : TransactionState

    data class Content(
        val isChecked: Boolean = false,
    ) : TransactionState
}

sealed interface TransactionIntent : MviIntent {

    data class ChbChanged(val isChecked: Boolean) : TransactionIntent
}

sealed interface TransactionEffect : Effect {

}