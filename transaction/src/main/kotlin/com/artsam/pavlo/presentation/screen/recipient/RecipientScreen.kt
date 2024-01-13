package com.artsam.pavlo.presentation.screen.recipient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artsam.pavlo.presentation.core.MviScreen
import com.artsam.pavlo.presentation.screen.recipient.RecipientIntent.*
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.*

class RecipientScreen(
    private val viewModel: RecipientViewModel,
    private val navigateRecipientScreen: (Boolean) -> Unit
) : MviScreen<RecipientState, RecipientIntent, RecipientEffect>(viewModel) {

    @Composable
    override fun Content() {
        ScreenContent(
            state = viewModel.state.collectAsStateWithLifecycle().value
        )
    }

    @Composable
    private fun ScreenContent(state: RecipientState) {
        Box(modifier = Modifier.fillMaxSize()) {

            when (state) {
                is Uninitialized -> Text(
                    text = "Loading ...",
                    modifier = Modifier.align(Alignment.Center)
                )
                is Content -> {
                    Text(
                        text = "Hello from Recipient screen",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Checkbox(
                        checked = state.isChecked,
                        onCheckedChange = { viewModel.handleIntent(ChbChanged(it)) },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(0.dp, 300.dp, 0.dp, 0.dp)
                    )
                    Button(
                        onClick = { navigateRecipientScreen(state.isChecked) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(0.dp, 0.dp, 0.dp, 40.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            }
        }
    }
}