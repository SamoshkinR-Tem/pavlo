package com.artsam.pavlo.presentation.screen.purpose

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

class PurposeOfPaymentScreen(
    private val viewModel: PurposeOfPaymentViewModel,
    private val navigateConfirmationScreen: (Boolean) -> Unit
) : MviScreen<PurposeOfPaymentState, PurposeOfPaymentIntent, PurposeOfPaymentEffect>(viewModel) {

    @Composable
    override fun Content() {
        ScreenContent(
            state = viewModel.state.collectAsStateWithLifecycle().value
        )
    }

    @Composable
    private fun ScreenContent(state: PurposeOfPaymentState) {
        Box(modifier = Modifier.fillMaxSize()) {

            when (state) {
                is PurposeOfPaymentState.Uninitialized -> Text(
                    text = "Loading ...",
                    modifier = Modifier.align(Alignment.Center)
                )
                is PurposeOfPaymentState.Content -> {
                    Text(
                        text = "Hello from PurposeOfPayment screen",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Checkbox(
                        checked = state.isBtnNextEnabled,
                        onCheckedChange = { viewModel.handleIntent(PurposeOfPaymentIntent.ChbChanged(it)) },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(0.dp, 300.dp, 0.dp, 0.dp)
                    )
                    Button(
                        enabled = state.isBtnNextEnabled,
                        onClick = { navigateConfirmationScreen(true) },
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
