package com.jazim.pixelnews.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jazim.pixelnews.R
import com.jazim.pixelnews.presentation.state.CoinDetailState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modifier: Modifier,
    onDismissed: () -> Unit,
    selectedCoinName: String?,
    coinDetailState: CoinDetailState,
    bottomSheetState: SheetState
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissed() },
        sheetState = bottomSheetState
    ) {
        when {
            coinDetailState.loading -> {
                Column(Modifier.padding(16.dp)) {
                    CircularProgressIndicator(modifier = Modifier.size(128.dp))
                    // We have the name already from the list of coins, while the API fetches logo and description
                    // might as well show the name instantly so the user knows what they're waiting for.
                    selectedCoinName?.let { coinName ->
                        Text(
                            text = coinName,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Row(modifier = Modifier.defaultMinSize(minHeight = 100.dp)) {
                        Text(text = stringResource(R.string.description),  style = MaterialTheme.typography.labelLarge)
                        CircularProgressIndicator()
                    }
                }
            }

            coinDetailState.error != null -> {
                Text(
                    text = stringResource(R.string.error, coinDetailState.error),
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }

            else -> {
                Column(Modifier.padding(16.dp)) {
                    if (coinDetailState.logo.isNullOrEmpty()) {
                        Icon(painterResource(R.drawable.baseline_image_not_supported_24),
                            stringResource(
                                R.string.image_not_found
                            ), modifier = Modifier.size(128.dp))
                    } else {
                        AsyncImage(model =  coinDetailState.logo, "coin logo for $selectedCoinName", modifier = Modifier.size(128.dp))
                    }

                    selectedCoinName?.let { coinName ->
                        Text(
                            text = coinName,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = stringResource(
                        R.string.description_detail,
                        coinDetailState.description.takeIf { !it.isNullOrEmpty() }
                            ?: "No description found"), style = MaterialTheme.typography.labelLarge, modifier = Modifier.defaultMinSize(minHeight = 100.dp))

                }
            }
        }
    }
}
