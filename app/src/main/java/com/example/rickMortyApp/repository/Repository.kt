package com.example.rickMortyApp.repository

import com.example.rickMortyApp.network.ApiService

class Repository(private val apiService: ApiService) {
    fun getCharacter(page:String) = apiService.fetchCharacter(page)
    fun getSingleCharacter(page:String) = apiService.fetchSingleCharacter(page)
    fun getLocation(page:String) = apiService.fetchLocation(page)
    fun getEpisode(page: String) = apiService.fetchEpisode(page)
}
