@file:Suppress("MemberVisibilityCanBePrivate")

package com.artsam.pavlo.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

abstract class MviScreen<S: MviState, I: MviIntent, E: Effect>(
    private val viewModel: MviViewModel<S, I, E>,
) : Screen() {

    @Composable
    override fun AsComposable() {
        LaunchedEffect(KEY_INTENTS_PROCESSOR) {
            viewModel.effectStream.collect(::processIntent)
        }
        super.AsComposable()
    }

    protected open fun processIntent(effect: E) = Unit

    companion object {
        private const val KEY_INTENTS_PROCESSOR = "key_intents_processor"
    }
}
