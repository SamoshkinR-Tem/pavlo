package com.artsam.pavlo.presentation.screen.recipient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.core.MviScreen
import com.artsam.pavlo.presentation.screen.recipient.RecipientIntent.*
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.*

class RecipientScreen(
    private val viewModel: RecipientViewModel,
    private val navigateIntermediaryBankScreen: (Boolean) -> Unit
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
                        checked = state.isBtnNextEnabled,
                        onCheckedChange = { viewModel.handleIntent(ChbChanged(it)) },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(0.dp, 300.dp, 0.dp, 0.dp)
                    )
                    Button(
                        enabled = state.isBtnNextEnabled,
                        onClick = { navigateIntermediaryBankScreen(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(20.dp, 0.dp, 20.dp, 20.dp),
                        shape = RectangleShape,
                    ) {
                        Text(
                            text = stringResource(R.string.btn_next),
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        }
    }
}
