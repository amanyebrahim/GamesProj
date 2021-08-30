package com.example.gamesproj.model.dataSource

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamesproj.model.api.ApiService
import com.example.gamesproj.model.dataClasses.GameDetails
import timber.log.Timber

class GameDatSource (private val apiService: ApiService) : PagingSource<Int, GameDetails>() {

    @SuppressLint("TimberArgCount", "LogNotTimber")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDetails> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            Timber.v("loading Key$currentLoadingPageKey")
            val response = apiService.getListGames(currentLoadingPageKey)
            val responseData = mutableListOf<GameDetails>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            Timber.v("GamePagingException${e.message}")
            return LoadResult.Error(e)
        }
    }

    @SuppressLint("TimberArgCount")
    override fun getRefreshKey(state: PagingState<Int, GameDetails>): Int? {
        Timber.v("anchorPosition","${state.anchorPosition}")
       return state.anchorPosition
    }
}