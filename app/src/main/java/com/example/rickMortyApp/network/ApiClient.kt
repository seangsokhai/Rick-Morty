package com.example.rickMortyApp.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//@Module
//@InstallIn(SingletonComponent::class)
object ApiClient {
    private const val BASE_URl = "https://rickandmortyapi.com/api/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}

interface ApiService{
    //create method to tell server send us those character
    @GET("character")
    fun fetchCharacter(@Query("page") page:String): Call<CharacterResponse>

    @GET("location")
    fun fetchLocation(@Query("page") page:String): Call<LocationResponse>

    @GET("location")
    fun fetchFilterLocation(@Query("page") page: String,
                            @Query("type") type: String,
                            @Query("dimension") dimension: String
                            ) : Call<LocationResponse>

    @GET("episode/")
    fun fetchEpisode(@Query("page") page:String): Call<EpisodeResponse>


    @GET("episode/")
    fun fetchSearchEpisode(@Query("page") page:String,
                           @Query("name") name:String
    ): Call<EpisodeResponse>

}