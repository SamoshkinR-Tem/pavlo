package com.artsam.pavlo.presentation.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.screen.bank.IntermediaryBankScreen
import com.artsam.pavlo.presentation.screen.confirmation.ConfirmationScreen
import com.artsam.pavlo.presentation.screen.purpose.PurposeOfPaymentScreen
import com.artsam.pavlo.presentation.screen.recipient.RecipientScreen
import com.artsam.pavlo.presentation.screen.transaction.TransactionScreen
import com.artsam.pavlo.presentation.theme.PavloTheme
import com.artsam.pavlo.presentation.topbar.HomeTopAppBar
import com.artsam.pavlo.presentation.topbar.TopAppBarState
import com.artsam.pavlo.presentation.topbar.TopAppBarState.OneRow
import com.artsam.pavlo.presentation.topbar.TopAppBarState.ThreeRows
import com.artsam.pavlo.presentation.topbar.TopAppBarState.TwoRows
import org.koin.androidx.compose.koinViewModel

private const val ROUTE_TRANSACTION = "transaction"
private const val ROUTE_RECIPIENT = "recipient-data"
private const val ROUTE_INTERMEDIARY_BANK = "intermediary-bank-data"
private const val ROUTE_PURPOSE_OF_PAYMENT = "purpose of-payment"
private const val ROUTE_CONFIRMATION = "confirmation"

@Composable
fun NavigationHost() {
    PavloTheme {
        val topAppBarState = remember { mutableStateOf<TopAppBarState>(TwoRows) }
        val navController = rememberNavController()
        val context: Context = LocalContext.current
        Scaffold(
            topBar = {
                HomeTopAppBar(
                    state = topAppBarState.value,
                    onNavigationIconClick = {
                        navController.previousBackStackEntry?.destination?.route?.let {
                            topAppBarState.value =
                                when (it) {
                                    ROUTE_RECIPIENT -> OneRow(R.string.topbar_recipient)
                                    ROUTE_INTERMEDIARY_BANK -> OneRow(R.string.topbar_intermediary_bank)
                                    ROUTE_PURPOSE_OF_PAYMENT -> OneRow(R.string.topbar_purpose_of_payment)
                                    else -> TwoRows
                                }
                            navController.popBackStack()
                        }
                    },
                    onActionIconClick = {
                        Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                startDestination = ROUTE_TRANSACTION,
            ) {
                composable(ROUTE_TRANSACTION) {
                    TransactionScreen(
                        viewModel = koinViewModel(),
                        navigateRecipientScreen = { isDataValid ->
                            if (isDataValid) {
                                navController.navigate(ROUTE_RECIPIENT)
                                topAppBarState.value = OneRow(R.string.topbar_recipient)
                            }
                        }
                    ).AsComposable()
                }
                composable(ROUTE_RECIPIENT) {
                    RecipientScreen(
                        viewModel = koinViewModel(),
                        navigateIntermediaryBankScreen = { isDataValid ->
                            if (isDataValid) {
                                navController.navigate(ROUTE_INTERMEDIARY_BANK)
                                topAppBarState.value = OneRow(R.string.topbar_intermediary_bank)
                            }
                        }
                    ).AsComposable()
                }
                composable(ROUTE_INTERMEDIARY_BANK) {
                    IntermediaryBankScreen(
                        viewModel = koinViewModel(),
                        navigatePurposeOfPaymentScreen = { isDataValid ->
                            if (isDataValid) {
                                navController.navigate(ROUTE_PURPOSE_OF_PAYMENT)
                                topAppBarState.value = OneRow(R.string.topbar_purpose_of_payment)
                            }
                        }
                    ).AsComposable()
                }
                composable(ROUTE_PURPOSE_OF_PAYMENT) {
                    PurposeOfPaymentScreen(
                        viewModel = koinViewModel(),
                        navigateConfirmationScreen = { isDataValid ->
                            if (isDataValid) {
                                navController.navigate(ROUTE_CONFIRMATION)
                                topAppBarState.value = ThreeRows
                            }
                        }
                    ).AsComposable()
                }
                composable(ROUTE_CONFIRMATION) {
                    ConfirmationScreen(viewModel = koinViewModel()).AsComposable()
                }
            }
        }
    }
}

@Preview()
@Composable
fun ShowHomeAppBar() {
    PavloTheme {
        HomeTopAppBar(state = ThreeRows)
    }
}
