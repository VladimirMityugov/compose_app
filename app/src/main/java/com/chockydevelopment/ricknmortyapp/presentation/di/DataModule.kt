package com.chockydevelopment.ricknmortyapp.presentation.di

import com.chockydevelopment.ricknmortyapp.data.remote.RickNMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRickNMortyApi():RickNMortyApi{
        return Retrofit.Builder()
            .baseUrl(RickNMortyApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RickNMortyApi::class.java)
    }

}