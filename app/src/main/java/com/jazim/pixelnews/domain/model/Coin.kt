package com.jazim.pixelnews.domain.model

data class Coin(
    val name: String? = null,
    val logo: String? = null,
    val description: String? = null,
    val links: List<List<String>> = emptyList()
)