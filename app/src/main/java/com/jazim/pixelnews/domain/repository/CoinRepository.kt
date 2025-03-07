package com.jazim.pixelnews.domain.repository

import com.jazim.pixelnews.data.models.Coin
import com.jazim.pixelnews.domain.model.ShortCoin

interface CoinRepository {
    suspend fun getCoins(): Result<List<ShortCoin>>
    suspend fun getCoinById(id: String): Result<Coin>
}