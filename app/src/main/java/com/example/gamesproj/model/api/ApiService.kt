package com.example.gamesproj.model.api

import com.example.gamesproj.model.dataClasses.GamesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * ApiService
 *
 */
interface ApiService {


    @GET("games?key=$KEY")
     suspend fun getListGames(pageNumber:Int): Response<GamesResponse>


    companion object {
        const val KEY = "292b0aa50deb45718ab98d806ad61fee"
    }

}