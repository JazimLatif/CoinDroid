package com.jazim.pixelnews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jazim.pixelnews.presentation.CoinViewModel
import com.jazim.pixelnews.presentation.coins.CoinsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController,
        Screen.CoinsScreen.route
    ) {
        composable(route = Screen.CoinsScreen.route) {
            val coinViewModel = hiltViewModel<CoinViewModel>()
            CoinsScreen(coinViewModel)
        }
    }
}