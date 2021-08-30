package com.example.gamesproj.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gamesproj.R
import com.example.gamesproj.model.api.ApiService
import com.example.gamesproj.model.dataClasses.ErrorResponse
import com.example.gamesproj.model.dataClasses.GameDetails
import com.example.gamesproj.model.dataSource.GameDatSource
import com.haroldadmin.cnradapter.NetworkResponse
import timber.log.Timber
import java.io.IOException

/**
 * ModelRepository
 */
class ModelRepository(
    private val _apiServices: ApiService
) {

    fun getGames() = Pager(
        config = PagingConfig(
            pageSize = GAMES_PAGE_SIZE,
        ), pagingSourceFactory = { GameDatSource(_apiServices) }
    ).liveData

    suspend fun getGameDetails(
        id:Int
    ): NetworkResponse<GameDetails, ErrorResponse> {
        return when (val response = _apiServices.getGameDetails(id).await()) {
            is NetworkResponse.Success -> response
            else -> handleError(response)
        }
    }

    companion object {
        private const val GAMES_PAGE_SIZE = 10
    }

    //endregion

    //region Private methods
    /**
     * Handle error while API call.
     * Search instead in local database and return the offline results.
     */
    private fun <T : Any> handleError(response: NetworkResponse<T, ErrorResponse>): NetworkResponse<T, ErrorResponse> {
        return when (response) {
            is NetworkResponse.ServerError -> when (response.code) {
                500 -> response.apply { body?.errorMessageId = R.string.server_error }
                401 -> response.apply { body?.errorMessageId = R.string.session_expired }
                else -> response
            }

            is NetworkResponse.NetworkError -> handleNetworkError(response.error)
            is NetworkResponse.UnknownError -> handleUnknownError(response.error)
            else -> response
        }
    }

    /**
     * Handle network error through converting the network error to server error.
     *
     * @param error The error happened for log purpose.
     */
    private fun <T : Any> handleNetworkError(
        error: IOException,
    ): NetworkResponse<T, ErrorResponse> {
        Timber.w(error, "Network Error")

        return NetworkResponse.ServerError(
            ErrorResponse(errorMessageId = R.string.connection_error,error = ""), 0
        )
    }

    /**
     * Handle unknown error through converting the unknown error to server error.
     *
     * @param error The error happened for log purpose.
     */
    private fun <T : Any> handleUnknownError(error: Throwable): NetworkResponse<T, ErrorResponse> {
        Timber.e(error, "Unknown Error")

        return NetworkResponse.ServerError(
            ErrorResponse(errorMessageId = R.string.unknown_error,error = ""), 0
        )
    }
    //endregion
}