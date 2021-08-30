package com.example.gamesproj.model.dataClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GamesResponse(
    @SerializedName( "count")
    val count: Int?,
    @SerializedName( "description")
    val description: String?,
    @SerializedName( "next")
    val next: String?,
    @SerializedName( "previous")
    val previous: String?,
    @SerializedName( "results")
    val results: List<GameDetails>?
):Parcelable