package com.jazim.pixelnews.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CoinListItemView(
    modifier: Modifier,
    name: String,
    symbol: String,
    onClick: () -> Unit
) {
    Row(modifier = modifier.clickable { onClick() }.fillMaxSize()) {
        Text(name)
        Spacer(modifier.padding(horizontal = 20.dp))
        Text(symbol)
    }
    HorizontalDivider(
        modifier = modifier.padding(),
        color = Color.Gray,
        thickness = 1.dp
    )
}