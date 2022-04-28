package com.example.rickMortyApp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiResidents {
    private const val BASE_URl = "https://rickandmortyapi.com/api/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val apiResidentsService: ApiResidentsService by lazy {
        retrofit.create(ApiResidentsService::class.java)
    }

}

interface ApiResidentsService{
    //create method to tell server send us those character
    @GET("character")
    fun fetchResidents(@Query("page") page:String): Call<ResidentsResponse>
}

