package com.example.gamesproj.model.dataClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * ErrorResponse
 */
@Parcelize
data class ErrorResponse(
    @SerializedName("error")
    val error: String?,
    var errorMessageId: Int?
):Parcelable