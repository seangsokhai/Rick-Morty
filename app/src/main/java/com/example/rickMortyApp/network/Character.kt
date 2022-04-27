package com.example.rickMortyApp.network

import com.squareup.moshi.Json

data class Character(@field:Json(name = "image") val image: String,
                     @field:Json(name = "name") var name: String,
                     @field:Json(name = "status") val status: String,
                     @field:Json(name = "species") val species: String,
                     @field:Json(name = "gender") val gender: String,
                     @field:Json(name = "url") val url: String,
                     @field:Json(name = "created") val created: String,
                     @field:Json(name = "location") val location: Location,
                     @field:Json(name = "episode") val episode: List<String>
                     )

data class CharacterResponse(@field:Json(name = "results")
                             val results: List<Character>
)
//
data class Location(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)

//
//data class ListOf(
//    val string: String
//)