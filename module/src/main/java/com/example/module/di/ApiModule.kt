package com.example.module.di

import com.example.domain.constants.Constants.BASE_URL
import com.example.module.service.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesWeatherApi(retrofit: Retrofit) : WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}