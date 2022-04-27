package com.example.rickMortyApp.network

import com.squareup.moshi.Json

data class EpisodeData(@field:Json(name = "id") val id: String,
                     @field:Json(name = "name") val name: String,
                     @field:Json(name = "air_date") val air_date: String,
                     @field:Json(name = "episode") val episode: String,
                     @field:Json(name = "created") val created: String,
                   @field:Json(name = "url") val url: String,
                     @field:Json(name = "characters") val characters: List<String>
                     )


data class EpisodeResponse(
//    @field:Json(name = "info") val info: Info,
    @field:Json(name = "results") val results: List<EpisodeData>
)

//data class Info(
//    @field:Json(name = "count") val count: Int,
//    @field:Json(name = "next") val next: String?,
//    @field:Json(name = "pages") val pages: Int,
//    @field:Json(name = "prev") val prev: String?
//)
