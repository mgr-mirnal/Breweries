package com.example.brewery.di

import com.example.brewery.data.remote.BreweryApiService
import com.example.brewery.data.repository.BreweryRepositoryImpl
import com.example.brewery.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openbrewerydb.org/"

@Module
@InstallIn(SingletonComponent ::class)
class ServiceModel {

    @Provides
    fun provideApiService(): BreweryApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreweryApiService::class.java)

    @Provides
    fun provideRepositoryLayer(service: BreweryApiService) : Repository =
        BreweryRepositoryImpl(service)

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}