package com.artsam.pavlo.presentation.topbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artsam.pavlo.R
import com.artsam.pavlo.presentation.topbar.TopAppBarState.*

sealed class TopAppBarState(val resId: Int) {

    class OneRow(titleResId: Int) : TopAppBarState(titleResId)
    data object TwoRows : TopAppBarState(R.string.topbar_transaction)
    data object ThreeRows : TopAppBarState(R.string.topbar_transaction)
}

@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    state: TopAppBarState = OneRow(0),
    onNavigationIconClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {},
) {
    ThreeRowsTopAppBar(
        modifier = modifier.clip(RoundedCornerShape(bottomStart = 24.dp)),
        mainRow = {
            MainTopBarRow(
                titleResId = state.resId,
                onNavigationIconClick,
                onActionIconClick,
            )
        },
        secondRow = { if (state is TwoRows || state is ThreeRows) SecondTopBarRow() },
        thirdRow = { if (state is ThreeRows) ThirdTopBarRow() },
    )
}

@Composable
fun ThreeRowsTopAppBar(
    modifier: Modifier = Modifier,
    mainRow: @Composable () -> Unit = {},
    secondRow: @Composable () -> Unit = {},
    thirdRow: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        mainRow()
        secondRow()
        thirdRow()
    }
}

@Composable
fun MainTopBarRow(
    titleResId: Int,
    onNavigationIconClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onNavigationIconClick,
            content = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Navigation Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            },
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = titleResId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = onActionIconClick,
            content = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Action Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            },
        )
    }
}

@Composable
fun SecondTopBarRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = stringResource(id = R.string.topbar_account_main),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 16.dp),
            painter = painterResource(id = R.drawable.ic_topbar_arrow),
            contentDescription = "Navigation Icon",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = stringResource(id = R.string.topbar_swift),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun ThirdTopBarRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(140.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke((0.5).dp, MaterialTheme.colorScheme.onPrimary),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    painter = painterResource(id = R.drawable.ic_topbar_coins),
                    contentDescription = "Coins icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp, top = 42.dp),
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    text = "200000,00"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    text = "01/20"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    text = "**** 1234"
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Card(
            modifier = Modifier
                .weight(1f)
                .height(140.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke((0.5).dp, MaterialTheme.colorScheme.onPrimary),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    painter = painterResource(id = R.drawable.ic_topbar_swift),
                    contentDescription = "SWIFT icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 24.dp),
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.topbar_transaction_swift)
                )
            }
        }
    }
}
