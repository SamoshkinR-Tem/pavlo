package com.artsam.pavlo.presentation.screen.recipient

import com.artsam.pavlo.presentation.core.Effect
import com.artsam.pavlo.presentation.core.MviIntent
import com.artsam.pavlo.presentation.core.MviState
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel.FieldTag

sealed interface RecipientState : MviState {

    data object Uninitialized : RecipientState

    data class Content(
        val account: String = "",
        val recipientName: String = "",
        val country: String = "Germany",
        val city: String = "",
        val address: String = "",
        val isBtnNextEnabled: Boolean = false,
    ) : RecipientState
}

sealed interface RecipientIntent : MviIntent {
    data class TextChange(val fieldTag: FieldTag, val value: String) : RecipientIntent
}

sealed interface RecipientEffect : Effect {}
