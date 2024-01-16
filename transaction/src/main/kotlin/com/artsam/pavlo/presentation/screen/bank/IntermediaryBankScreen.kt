package com.artsam.pavlo.presentation.screen.bank

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

class IntermediaryBankScreen(
    private val viewModel: IntermediaryBankViewModel,
    private val navigatePurposeOfPaymentScreen: (Boolean) -> Unit
) : MviScreen<IntermediaryBankState, IntermediaryBankIntent, IntermediaryBankEffect>(viewModel) {

    @Composable
    override fun Content() {
        ScreenContent(
            state = viewModel.state.collectAsStateWithLifecycle().value
        )
    }

    @Composable
    private fun ScreenContent(state: IntermediaryBankState) {
        Box(modifier = Modifier.fillMaxSize()) {

            when (state) {
                is IntermediaryBankState.Uninitialized -> Text(
                    text = "Loading ...",
                    modifier = Modifier.align(Alignment.Center)
                )
                is IntermediaryBankState.Content -> {
                    Text(
                        text = "Intermediary bank screen",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Checkbox(
                        checked = state.isChecked,
                        onCheckedChange = {
                            viewModel.handleIntent(
                                IntermediaryBankIntent.ChbChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(0.dp, 300.dp, 0.dp, 0.dp)
                    )
                    Button(
                        onClick = { navigatePurposeOfPaymentScreen(state.isChecked) },
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
