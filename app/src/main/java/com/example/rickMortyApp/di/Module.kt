package com.example.rickMortyApp.di

import com.example.rickMortyApp.network.ApiService
import com.example.rickMortyApp.repository.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class Module {


    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun retrofit(moshi: Moshi): Retrofit =  Retrofit.Builder()

        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun api(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun repo(api: ApiService): Repository = Repository(api)
}