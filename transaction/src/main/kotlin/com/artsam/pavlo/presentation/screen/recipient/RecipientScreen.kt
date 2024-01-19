package com.artsam.pavlo.presentation.screen.recipient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.core.MviScreen
import com.artsam.pavlo.presentation.core.item.TransactionFieldItem
import com.artsam.pavlo.presentation.screen.recipient.RecipientIntent.TextChange
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.Content
import com.artsam.pavlo.presentation.screen.recipient.RecipientState.Uninitialized
import com.artsam.pavlo.presentation.screen.recipient.RecipientViewModel.FieldTag
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
                modifier = Modifier.fillMaxSize(),
                state = state,
                onTextChange = { tag, value -> viewModel.handleIntent(TextChange(tag, value)) },
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
    onTextChange: (FieldTag, String) -> Unit = { _, _ -> },
    navigateIntermediaryBankScreen: (Boolean) -> Unit = {},
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val itemModifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

            Text(
                text = stringResource(R.string.recipient_description),
                fontSize = 14.sp
            )

            TransactionFieldItem(
                defaultValue = state.account,
                onValueChange = { onTextChange(FieldTag.ACCOUNT, it) },
                label = { Text(stringResource(R.string.label_account)) },
                readOnly = false,
                modifier = itemModifier,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )

            TransactionFieldItem(
                defaultValue = state.recipientName,
                onValueChange = { onTextChange(FieldTag.RECIPIENT_NAME, it) },
                label = { Text(stringResource(R.string.label_name)) },
                readOnly = false,
                modifier = itemModifier,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )

            TransactionFieldItem(
                defaultValue = state.country,
                onValueChange = { onTextChange(FieldTag.COUNTRY, it) },
                label = { Text(stringResource(R.string.label_country)) },
                modifier = itemModifier,
                onTrailingIconClick = {}
            )

            TransactionFieldItem(
                defaultValue = state.city,
                onValueChange = { onTextChange(FieldTag.CITY, it) },
                label = { Text(stringResource(R.string.label_city)) },
                readOnly = false,
                modifier = itemModifier,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            )

            TransactionFieldItem(
                defaultValue = state.address,
                onValueChange = { onTextChange(FieldTag.ADDRESS, it) },
                label = { Text(stringResource(R.string.label_address)) },
                readOnly = false,
                modifier = itemModifier,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
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
        ShowContent(state = Content())
    }
}
