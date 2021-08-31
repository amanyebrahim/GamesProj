package com.example.gamesproj.model.api

import com.example.gamesproj.BuildConfig
import com.example.gamesproj.model.dataClasses.ErrorResponse
import com.example.gamesproj.model.dataClasses.GameDetails
import com.example.gamesproj.model.dataClasses.GamesResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * ApiService
 *
 */
interface ApiService {

    @GET("games?key=$KEY")
     suspend fun getListGames(@Query("page") page:Int): Response<GamesResponse>

    @GET("games/{id}?key=$KEY")
    fun getGameDetails(@Path("id") id:Int): Deferred<NetworkResponse<GameDetails,ErrorResponse>>


    companion object {
        const val KEY = BuildConfig.key
    }

}