package com.jazim.pixelnews.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jazim.pixelnews.presentation.navigation.Navigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PixelNewsApp() {
    val navController = rememberNavController()
    Scaffold {
        Navigation(navController)
    }
}