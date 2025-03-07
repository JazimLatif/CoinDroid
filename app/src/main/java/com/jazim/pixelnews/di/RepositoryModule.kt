package com.jazim.pixelnews.di

import com.jazim.pixelnews.data.repository.CoinRepositoryImpl
import com.jazim.pixelnews.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCoinRepository(
        creditRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository
}