package com.jazim.pixelnews.presentation.coins

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
    val coinState = coinViewModel.coinState.value

    Box(Modifier.fillMaxSize().testTag("MainScreen"), contentAlignment = Alignment.Center) {
        when {
            coinState.loading -> CircularProgressIndicator(Modifier.testTag("LoadingIndicator"))


            coinState.error != null -> {
                Text(modifier = Modifier.width(300.dp), text = coinState.error.toString())
            }

            else -> {
                val sortedCoinNames = coinState.coinNames.sorted()
                println("size is ${coinState.coinNames.size}")
                LazyColumn {
                    items(sortedCoinNames) {
                        Text(it)
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