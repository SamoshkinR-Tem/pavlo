package com.artsam.pavlo.presentation.screen.bank

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState

sealed interface IntermediaryBankState : MviState {

    data object Uninitialized : IntermediaryBankState

    data class Content(
        val isChecked: Boolean = false,
    ) : IntermediaryBankState
}

sealed interface IntermediaryBankIntent : MviIntent {

    data class ChbChanged(val isChecked: Boolean) : IntermediaryBankIntent
}

sealed interface IntermediaryBankEffect : Effect {

}
