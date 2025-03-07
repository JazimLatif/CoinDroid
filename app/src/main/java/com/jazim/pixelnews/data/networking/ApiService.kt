package com.jazim.pixelnews.data.networking

import com.jazim.pixelnews.data.models.Coin
import com.jazim.pixelnews.data.models.ShortCoin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/coins/")
    suspend fun getCoins(): Response<List<ShortCoin>>

    @GET("v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): Response<Coin>
}