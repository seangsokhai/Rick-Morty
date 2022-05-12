package com.example.rickMortyApp.repository

import android.view.View
import com.example.rickMortyApp.network.*

class Repository(private val apiService: ApiService) {
    //fun getCharacter(page:String) = apiService.fetchCharacter(page)
    //fun getLocation(page:String) = apiService.fetchLocation(page)

    suspend fun getCharacter(page: String): CharacterResponse?{
        return try {
            apiService.fetchCharacterSuspend(page)
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
//    suspend fun getFilterCharacter(page: String , name: String): CharacterResponse? {
//        return try{
//            apiService.fetchFilterCharacter(page, name)
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
    suspend fun getFilterEpisodes(page: String , name: String): EpisodeResponse? {
        return try{
            apiService.fetchFilterEpisode(page, name)
        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getLocation(page: String): LocationResponse? {
        return try{
            apiService.fetchLocation(page)

        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getFilterLocation(page: String , type : String , dimension : String): LocationResponse? {
        return try{
            apiService.fetchFilterLocation(page, type , dimension)
        }catch(e: Exception){
            e.printStackTrace()
            null
        }
    }

//    suspend fun getLocation(): LocationDto? {
//        return try  {
//            apiService.getLocation()
//        }catch (e: Exception){
//            e.printStackTrace()
//            null
//        }
//    }

    //    suspend fun getCharacterDI(page: String): CharacterResponse? {
//        return try{
//            apiService.fetchCharacterDI(page)
//        }catch(e: Exception){
//            e.printStackTrace()
//            null
//        }
//    }
}
