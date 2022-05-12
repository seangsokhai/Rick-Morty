package com.example.rickMortyApp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

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




// as same as above make it easier to fetch key from api
//@Parcelize
//@JsonClass(generateAdapter = true)
//data class LocationDto(
//    val created: String,
//    val dimension: String,
//    val id: Int,
//    val name: String,
//    val residents: List<String>,
//    val type: String,
//    val url: String
//) : Parcelable

