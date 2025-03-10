package com.jazim.pixelnews.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
    id: String,
    name: String,
    onClick: () -> Unit
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Text(name)
        HorizontalDivider(
            modifier = Modifier.padding(),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}