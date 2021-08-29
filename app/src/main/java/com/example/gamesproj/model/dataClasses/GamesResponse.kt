package com.example.gamesproj.model.dataClasses

import com.squareup.moshi.Json


data class GamesResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val results: List<GameDetails>?
)