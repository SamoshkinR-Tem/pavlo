@file:Suppress("unused")

package com.artsam.pavlo.presentation.core

interface MviState
interface MviIntent
interface Effect

object EmptyState : MviState
object EmptyIntent : MviIntent
object EmptyEffect : Effect
