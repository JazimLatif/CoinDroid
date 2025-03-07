package com.jazim.pixelnews.presentation.state

data class CoinsState(
    val loading: Boolean = true,
    val error: String? = null,
    val coinNames: List<String> = listOf("")
)