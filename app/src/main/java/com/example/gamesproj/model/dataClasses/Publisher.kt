package com.example.gamesproj.model.dataClasses

import com.squareup.moshi.Json


data class Publisher(
    @Json(name = "games_count")
    val games_count: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_background")
    val image_background: String?,
    @Json(name = "name")
    val name: String?
)