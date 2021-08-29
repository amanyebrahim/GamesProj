package com.example.gamesproj.model.dataClasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



data class GameDetails(
    @Json(name = "background_image")
    val background_image: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "movies_count")
    val movies_count: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "name_original")
    val name_original: String?,
    @Json(name = "publishers")
    val publishers: List<Publisher>?,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "ratings_count")
    val ratings_count: Int?,
    @Json(name = "released")
    val released: String?
)