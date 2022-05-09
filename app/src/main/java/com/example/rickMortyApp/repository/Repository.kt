package com.example.rickMortyApp.repository

import com.example.rickMortyApp.network.ApiService
import com.example.rickMortyApp.network.CharacterResponse
import com.example.rickMortyApp.network.EpisodeResponse
import com.example.rickMortyApp.network.LocationResponse

class Repository(private val apiService: ApiService) {
    fun getCharacter(page:String) = apiService.fetchCharacter(page)
    fun getLocation(page:String) = apiService.fetchLocation(page)


//    suspend fun getCharacterDI(page: String): CharacterResponse? {
//        return try{
//            apiService.fetchCharacterDI(page)
//        }catch(e: Exception){
//            e.printStackTrace()
//            null
//        }
//    }
//    suspend fun getLocation(page: String): LocationResponse? {
//        return try{
//            apiService.fetchLocation(page)
//        }catch(e: Exception){
//            e.printStackTrace()
//            null
//        }
//    }
    suspend fun getEpisodes(page: String): EpisodeResponse? {
        return try{
            apiService.fetchEpisode(page)
        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }


}
