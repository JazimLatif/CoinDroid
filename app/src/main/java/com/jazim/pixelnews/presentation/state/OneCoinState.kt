package com.jazim.pixelnews.presentation.state

data class OneCoinState(
    val loading: Boolean = true,
    val error: String? = null,
    val name: String? = null,
    val logo: String? = null,
    val description: String? = null,
    val links: List<List<String>> = emptyList()
)