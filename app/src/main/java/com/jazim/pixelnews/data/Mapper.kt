package com.jazim.pixelnews.data

import com.jazim.pixelnews.domain.model.Coin
import com.jazim.pixelnews.domain.model.ShortCoin

fun List<com.jazim.pixelnews.data.models.ShortCoinDto>.toDomainModel(): List<ShortCoin> {
    return this.map { shortCoin ->
        ShortCoin(id = shortCoin.id, name = shortCoin.name, symbol = shortCoin.symbol)
    }
}

fun com.jazim.pixelnews.data.models.CoinDto.toDomainModel(): Coin {
    return Coin(
        name = this.name,
        logo = this.logo,
        description = this.description,
        links = listOf(
            this.links.explorer,
            this.links.reddit,
            this.links.facebook,
            this.links.youtube
        )
    )
}
