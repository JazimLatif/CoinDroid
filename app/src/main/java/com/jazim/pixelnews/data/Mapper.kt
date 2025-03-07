package com.jazim.pixelnews.data

import com.jazim.pixelnews.domain.model.ShortCoin

fun List<com.jazim.pixelnews.data.models.ShortCoin>.toDomainModel(): List<ShortCoin> {
    return this.map { shortCoin ->
        ShortCoin(name = shortCoin.name)
    }
}
