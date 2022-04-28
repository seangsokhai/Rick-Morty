package com.example.rickMortyApp.network

import com.squareup.moshi.Json

data class LocationData(@field:Json(name = "type") val type: String,
                     @field:Json(name = "name") var name: String,
                     @field:Json(name = "dimension") val dimension: String,
                     @field:Json(name = "id") val id: String,
                     @field:Json(name = "url") val url: String,
                     @field:Json(name = "created") val created: String,
                     @field:Json(name = "residents") val residents: List<String>
)


data class LocationResponse(@field:Json(name = "results")
                             val results: List<LocationData>
)


data class LocationResidents(@field:Json(name = "status") val status: String?,
                             @field:Json(name = "name") var name: String,
                             @field:Json(name = "species") val species: String?,
                             @field:Json(name = "type") val type: String?,
                             @field:Json(name = "gender") val gender: String?,
                             @field:Json(name = "origin") val origin: Origin?,
                             @field:Json(name = "location") val location: LocationJson?,
                             @field:Json(name = "image") val image: String?,
                             @field:Json(name = "episode") val episode: List<String>?,
                             @field:Json(name = "url") val url: String?,
                             @field:Json(name = "created") val created: String?

)

data class Origin(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String


    )
data class LocationJson(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)




