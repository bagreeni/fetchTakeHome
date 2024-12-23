package com.bagreeni.fetchrewardstakehome.backend

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FetchApiModule {
    @Provides
    @Singleton
    fun providesFetchApi() : FetchApi{
        return FetchApiImpl("https://fetch-hiring.s3.amazonaws.com")
    }

    @Provides
    @Singleton
    fun providesFetchApiService(fetchApi: FetchApi) : FetchApiService{
        return FetchApiService(fetchApi)
    }
}
