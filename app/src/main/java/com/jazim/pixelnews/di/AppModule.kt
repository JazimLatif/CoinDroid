package com.jazim.pixelnews.di

import com.jazim.pixelnews.data.networking.ApiService
import com.jazim.pixelnews.data.networking.Networking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Networking.createRetrofit().create(ApiService::class.java)
    }
}