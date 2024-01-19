package com.artsam.pavlo.presentation.screen.recipient

import com.artsam.pavlo.presentation.core.MviViewModel
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.Content
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel.FieldTag.*

class RecipientViewModel :
    MviViewModel<RecipientState, RecipientIntent, RecipientEffect>() {

    override val emptyState = RecipientState.Uninitialized

    init {
        setState(Content())
    }

    override fun handleIntent(intent: RecipientIntent) {
        when (intent) {
            is RecipientIntent.TextChange -> onTextChange(intent.fieldTag, intent.value)
        }
    }

    private fun onTextChange(tag: FieldTag, value: String) {
        val cs = currentState as? Content
        when (tag) {
            ACCOUNT -> cs?.copy(account = value)?.let(::setState)
            RECIPIENT_NAME -> cs?.copy(recipientName = value)?.let(::setState)
            COUNTRY -> cs?.copy(country = value)?.let(::setState)
            CITY -> cs?.copy(city = value)?.let(::setState)
            ADDRESS -> cs?.copy(address = value)?.let(::setState)
        }
        validateData()
    }

    private fun validateData() {
        val cs = currentState as? Content
        cs?.copy(
            isBtnNextEnabled = cs.account.isNotBlank() &&
                    cs.recipientName.isNotBlank() &&
                    cs.country.isNotBlank() &&
                    cs.city.isNotBlank() &&
                    cs.address.isNotBlank()
        )?.let(::setState)
    }

    enum class FieldTag { ACCOUNT, RECIPIENT_NAME, COUNTRY, CITY, ADDRESS }
}
