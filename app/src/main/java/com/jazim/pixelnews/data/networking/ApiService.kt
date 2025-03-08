package com.jazim.pixelnews.data.networking

import com.jazim.pixelnews.data.models.CoinDto
import com.jazim.pixelnews.data.models.ShortCoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/coins/")
    suspend fun getCoins(): Response<List<ShortCoinDto>>

    @GET("v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): Response<CoinDto>
}