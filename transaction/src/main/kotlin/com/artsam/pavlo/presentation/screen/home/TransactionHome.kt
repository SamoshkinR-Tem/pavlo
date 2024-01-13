package com.artsam.pavlo.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artsam.pavlo.presentation.screen.recipient.RecipientScreen
import com.artsam.pavlo.presentation.screen.transaction.TransactionScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionHome() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ROUTE_TRANSACTION,
    ) {
        composable(ROUTE_TRANSACTION) {
            TransactionScreen(
                viewModel = koinViewModel(),
                navigateRecipientScreen = { isDataValid ->
                    println("test isDataValid $isDataValid")
                    println("test from TransactionScreen")
                    if (isDataValid) {
                        navController.navigate(ROUTE_RECIPIENT)
                    }
                }
            ).AsComposable()
        }
        composable(ROUTE_RECIPIENT) {
            RecipientScreen(
                viewModel = koinViewModel(),
                navigateRecipientScreen = { isDataValid ->
                    println("test isDataValid $isDataValid")
                    println("test from RecipientScreen")
                    if (isDataValid) {
                        navController.navigate(ROUTE_RECIPIENT)
                    }
                }
            ).AsComposable()
        }
//        composable(ROUTE_INTERMEDIARY_BANK) {
//        }
//        composable(ROUTE_PURPOSE_OF_PAYMENT) {
//        }
//        composable(ROUTE_CONFIRMATION) {
//        }
    }
}

private const val ROUTE_TRANSACTION = "transaction"
private const val ROUTE_RECIPIENT = "recipient-data"
private const val ROUTE_INTERMEDIARY_BANK = "intermediary-bank-data"
private const val ROUTE_PURPOSE_OF_PAYMENT = "purpose of-payment"
private const val ROUTE_CONFIRMATION = "confirmation"
