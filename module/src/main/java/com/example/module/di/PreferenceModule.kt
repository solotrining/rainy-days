package com.example.module.di

import android.content.Context
import android.content.SharedPreferences
import com.example.module.local.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferenceModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences
            = PreferenceManager.getPreferences(context)

    @Singleton
    @Provides
    fun providePreferencesManager(preferences: SharedPreferences): PreferenceManager
            = PreferenceManager(preferences)

}