package com.jazim.pixelnews.presentation.coins

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jazim.pixelnews.R
import com.jazim.pixelnews.presentation.CoinViewModel
import com.jazim.pixelnews.presentation.components.CoinListItemView
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsScreen(
    coinViewModel: CoinViewModel
) {

    val allCoinsState = coinViewModel.allCoinsState.value

    val coinDetailState = coinViewModel.coinDetailState.value

    val bottomSheetState = rememberModalBottomSheetState()

    var selectedCoinId by remember { mutableStateOf<String?>(null) }
    var selectedCoinName by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    Text("Coins Count : ${allCoinsState.coins.size}", fontSize = 20.sp)

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)

            .padding(top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        when {

            allCoinsState.loading -> CircularProgressIndicator(Modifier.testTag("LoadingIndicator"))

            allCoinsState.error != null -> {
                Text(modifier = Modifier.width(300.dp), text = allCoinsState.error.toString())
            }

            else -> {
                val sortedCoinNames = coinViewModel.getNamesAlphabetically()
                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = {
                        coinViewModel.getAllCoins()
                    }
                ) {
                    LazyColumn {
                        items(allCoinsState.coins) { coin ->
                            CoinListItemView(
                                modifier = Modifier,
                                id = coin.id,
                                name = coin.name,
                                onClick = {
                                    val clickedCoin =
                                        allCoinsState.coins.find { it.id == coin.id }
                                    selectedCoinId = clickedCoin?.id
                                    selectedCoinName = clickedCoin?.name
                                    selectedCoinId?.let { id -> coinViewModel.getCoin(id) }
                                    coroutineScope.launch { bottomSheetState.show() }
                                }
                            )
                        }
                    }
                }

            }
        }
    }

    if (selectedCoinId != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedCoinId = null },
            sheetState = bottomSheetState
        ) {
            when {
                coinDetailState.loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                coinDetailState.error != null -> {
                    Text(
                        text = "Error: ${coinDetailState.error}",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Red
                    )
                }

                else -> {
                    Column(Modifier.padding(16.dp)) {
                        if (coinDetailState.logo.isNullOrEmpty()) {
                            Icon(painterResource(R.drawable.baseline_image_not_supported_24), "image not found", modifier = Modifier.size(128.dp))
                        } else {
                            AsyncImage(model =  coinDetailState.logo, "coin logo for $selectedCoinName", modifier = Modifier.size(128.dp))
                        }

                        // We have the name already from the list of coins, while the API fetches logo and description
                        // might as well show the name instantly so the user knows what they're waiting for.
                        selectedCoinName?.let { coinName ->
                            Text(
                                text = coinName,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Description: ${coinDetailState.description.takeIf { !it.isNullOrEmpty() } ?: "No description found"}", style = MaterialTheme.typography.labelLarge, modifier = Modifier.height(100.dp))

                    }
                }
            }
        }
    }
}

