package com.jazim.pixelnews.presentation.coins

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.jazim.pixelnews.presentation.CoinViewModel

@Composable
fun CoinsScreen(
    coinViewModel: CoinViewModel
) {
    val allCoinsState = coinViewModel.allCoinsState.value
    var selectedCoinId by remember { mutableStateOf<String?>(null) }

    Box(Modifier.fillMaxSize().testTag("MainScreen"), contentAlignment = Alignment.Center) {
        when {
            allCoinsState.loading -> CircularProgressIndicator(Modifier.testTag("LoadingIndicator"))

            allCoinsState.error != null -> {
                Text(modifier = Modifier.width(300.dp), text = allCoinsState.error.toString())
            }

            else -> {
                val sortedCoinNames = allCoinsState.coins.map { it.name }.sorted()

                println("size is ${allCoinsState.coins.size}")

                LazyColumn {
                    items(sortedCoinNames) {
                        // IndividualShortCoinItem()
                        Text(it, modifier = Modifier.clickable {
                            val clickedCoin = allCoinsState.coins.find { coin -> coin.name == it }
                            selectedCoinId = clickedCoin?.id
                            println("clicked coinId is: $selectedCoinId")
                        })
                        HorizontalDivider(
                            modifier = Modifier.padding(),
                            color = Color.Gray,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }

}