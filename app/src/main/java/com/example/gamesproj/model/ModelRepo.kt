package com.example.gamesproj.model

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.gamesproj.model.dataClasses.ErrorResponse
import com.example.gamesproj.model.dataClasses.GameDetails
import com.haroldadmin.cnradapter.NetworkResponse

interface ModelRepo {


    fun getGames(): LiveData<PagingData<GameDetails>>

    suspend fun getGameDetails(
        id:Int
    ): NetworkResponse<GameDetails, ErrorResponse>


}