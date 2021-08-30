package com.example.gamesproj.model.dataClasses


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetails(
    @SerializedName("background_image")
    val background_image: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("movies_count")
    val movies_count: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName( "name_original")
    val name_original: String?,
    @SerializedName( "rating")
    val rating: Double?,
    @SerializedName( "ratings_count")
    val ratings_count: Int?,
    @SerializedName( "released")
    val released: String?
):Parcelable