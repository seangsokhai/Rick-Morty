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




