package com.example.gamesproj.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gamesproj.model.api.ApiService
import com.example.gamesproj.model.dataSource.GameDatSource

/**
 * ModelRepository
 */
class ModelRepository(
    private val _apiServices: ApiService
) {

//    fun getGamesList(pageNumber: Int): Response<GamesResponse> {
//       return _apiRepository.getListGames(pageNumber).await()
//    }



    init{

    }
    fun getGames() = Pager(
        config = PagingConfig(
            pageSize = IMAGE_PAGE_SIZE,
        ), pagingSourceFactory = { GameDatSource(_apiServices) }
    ).liveData

    companion object {
        private const val IMAGE_PAGE_SIZE = 10
        private const val IMAGE_MAX_SIZE = 50
    }

    //region Model repository methods

//    override suspend fun login(
//            phone: String,
//            password: String,
//            deviceId: String,
//            fcmToken: String?
//    ): NetworkResponse<LoginResponse, ErrorResponse> {
//        return when (val response = _apiRepository.login(
//                phone, password, deviceId, fcmToken
//        ).await()) {
//            is NetworkResponse.Success -> response
//            else -> handleError(response)
//        }
//    }response

    //endregion

    //region Private methods
    /**
     * Handle error while API call.
     * Search instead in local database and return the offline results.
     */
    //endregion
}