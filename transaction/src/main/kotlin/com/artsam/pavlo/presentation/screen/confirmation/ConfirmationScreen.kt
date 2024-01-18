package com.artsam.pavlo.presentation.screen.confirmation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.core.MviScreen
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationIntent.PostTransactionData
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationIntent.ToastShowed

class ConfirmationScreen(
    private val viewModel: ConfirmationViewModel,
) : MviScreen<ConfirmationState, ConfirmationIntent, ConfirmationEffect>(viewModel) {

    @Composable
    override fun Content() {
        ScreenContent(
            state = viewModel.state.collectAsStateWithLifecycle().value
        )
    }

    @Composable
    private fun ScreenContent(state: ConfirmationState) {
        Box(modifier = Modifier.fillMaxSize()) {
            val context: Context = LocalContext.current
            when (state) {
                is ConfirmationState.Uninitialized -> Text(
                    text = "Loading ...",
                    modifier = Modifier.align(Alignment.Center)
                )
                is ConfirmationState.Content -> {
                    if (state.isShowToast) {
                        Toast.makeText(context, "Transaction Data pushed", Toast.LENGTH_SHORT).show()
                        viewModel.handleIntent(ToastShowed)
                    }
                    Text(
                        text = "Confirmation screen",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Button(
                        enabled = !state.isLoading,
                        onClick = { viewModel.handleIntent(PostTransactionData) },
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
