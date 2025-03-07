package com.jazim.pixelnews.data.repository

import com.jazim.pixelnews.data.models.Coin
import com.jazim.pixelnews.data.networking.ApiService
import com.jazim.pixelnews.data.toDomainModel
import com.jazim.pixelnews.domain.model.ShortCoin
import com.jazim.pixelnews.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CoinRepository {

    override suspend fun getCoins(): Result<List<ShortCoin>> {
        return try {
            val response = apiService.getCoins()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse.toDomainModel())
                } else {
                    Result.failure(Throwable("Response body is null"))
                }
            } else {
                Result.failure(Throwable("Failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCoinById(id: String): Result<Coin> {
        return try {
            val response = apiService.getCoinById(id)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse)
                } else {
                    Result.failure(Throwable("Response body is null"))
                }
            } else {
                Result.failure(Throwable("Failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }    }


}