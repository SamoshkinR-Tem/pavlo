package com.artsam.pavlo.presentation.screen.confirmation

import androidx.lifecycle.viewModelScope
import com.artsam.pavlo.domain.usecase.PostTransactionDataUseCase
import com.artsam.pavlo.presentation.core.MviViewModel
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationIntent.PostTransactionData
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationState.Content
import kotlinx.coroutines.launch

class ConfirmationViewModel(
    private val postTransactionDataUseCase: PostTransactionDataUseCase,
) :
    MviViewModel<ConfirmationState, ConfirmationIntent, ConfirmationEffect>() {

    override val emptyState = ConfirmationState.Uninitialized

    init {
        setState(Content())
    }

    override fun handleIntent(intent: ConfirmationIntent) {
        when (intent) {
            PostTransactionData -> viewModelScope.launch {
                showLoading(true)
                postTransactionDataUseCase.invoke()
                    .onSuccess { showToast(true) }
            }
            ConfirmationIntent.ToastShowed -> showToast(false)
        }
    }

    private fun showLoading(value: Boolean) = (currentState as? Content)
        ?.copy(isLoading = value)
        ?.let(::setState)

    private fun showToast(value: Boolean) = (currentState as? Content)
        ?.copy(isShowToast = value, isLoading = false)
        ?.let(::setState)
}
