package com.artsam.pavlo.presentation.screen.recipient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.core.MviScreen
import com.artsam.pavlo.presentation.core.item.TransactionFieldItem
import com.artsam.pavlo.presentation.screen.recipient.RecipientIntent.ChbChanged
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.Content
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.Uninitialized
import com.artsam.pavlo.presentation.theme.PavloTheme

class RecipientScreen(
    private val viewModel: RecipientViewModel,
    private val navigateIntermediaryBankScreen: (Boolean) -> Unit
) : MviScreen<RecipientState, RecipientIntent, RecipientEffect>(viewModel) {

    @Composable
    override fun Content() {
        HandleState(
            state = viewModel.state.collectAsStateWithLifecycle().value
        )
    }

    @Composable
    private fun HandleState(state: RecipientState) {
        when (state) {
            is Uninitialized -> ShowLoading()
            is Content -> ShowContent(
                state = state,
                onCheckedChange = { viewModel.handleIntent(ChbChanged(it)) },
                navigateIntermediaryBankScreen = navigateIntermediaryBankScreen,
            )
        }
    }
}


@Composable
private fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Loading ...",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowContent(
    modifier: Modifier = Modifier,
    state: Content,
    onCheckedChange: (Boolean) -> Unit = {},
    navigateIntermediaryBankScreen: (Boolean) -> Unit = {},
) {
    var text by remember { mutableStateOf("Qwerty") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

            TransactionFieldItem(
                defaultValue = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Label") },
                placeholder = { Text(text = "Enter text...") },
                onTrailingIconClick = {}
            )

            TransactionFieldItem(
                defaultValue = text,
                onValueChange = { text = it },
                modifier = itemModifier,
                label = { Text("Label") },
                placeholder = { Text(text = "Enter text...") },
                onTrailingIconClick = {}
            )

            TransactionFieldItem(
                defaultValue = text,
                onValueChange = { text = it },
                modifier = itemModifier,
                label = { Text("Label") },
                placeholder = { Text(text = "Enter text...") },
                onTrailingIconClick = {}
            )

            Checkbox(
                checked = state.isBtnNextEnabled,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.padding(0.dp, 300.dp, 0.dp, 0.dp)
            )
        }

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
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    PavloTheme {
        ShowContent(state = Content(false))
    }
}
