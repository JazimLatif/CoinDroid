package com.jazim.pixelnews.data.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}