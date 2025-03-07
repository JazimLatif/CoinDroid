package com.jazim.pixelnews.presentation.navigation

sealed class Screen(val route: String) {
    object CoinsScreen: Screen("coinsscreen")
}