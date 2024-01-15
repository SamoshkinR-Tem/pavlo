package com.artsam.pavlo.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.screen.recipient.RecipientScreen
import com.artsam.pavlo.presentation.screen.transaction.TransactionScreen
import com.artsam.pavlo.presentation.theme.PavloTheme
import org.koin.androidx.compose.koinViewModel

private const val ROUTE_TRANSACTION = "transaction"
private const val ROUTE_RECIPIENT = "recipient-data"
private const val ROUTE_INTERMEDIARY_BANK = "intermediary-bank-data"
private const val ROUTE_PURPOSE_OF_PAYMENT = "purpose of-payment"
private const val ROUTE_CONFIRMATION = "confirmation"

@Composable
fun TransactionHome() {

    PavloTheme {
        val topAppBarState = remember { mutableIntStateOf(R.string.title_transaction) }
        val navController = rememberNavController()

        Scaffold(
            topBar = { HomeTopAppBar(topAppBarState, navController) }
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
                                topAppBarState.intValue = R.string.title_recipient
                            }
                        }
                    ).AsComposable()
                }
                composable(ROUTE_RECIPIENT) {
                    RecipientScreen(
                        viewModel = koinViewModel(),
                        navigateRecipientScreen = { isDataValid ->

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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(topAppBarState: MutableIntState, navController: NavHostController) {

    CenterAlignedTopAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 24.dp)),
        title = {
            Text(
                text = stringResource(id = topAppBarState.intValue),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back button",
                    )
                },
            )
        },
        actions = {
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                },
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Preview()
@Composable
fun ShowHomeAppBar() {
    val topAppBarState = remember { mutableIntStateOf(R.string.title_transaction) }
    val navController = rememberNavController()

    PavloTheme {
        HomeTopAppBar(topAppBarState, navController)
    }
}