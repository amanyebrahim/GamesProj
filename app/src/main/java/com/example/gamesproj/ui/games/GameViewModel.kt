package com.example.gamesproj.ui.games


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gamesproj.model.ModelRepository
import com.example.gamesproj.model.dataClasses.GameDetails



class GameViewModel (private val _model: ModelRepository) : ViewModel(){

    var gamePagingData:LiveData<PagingData<GameDetails>>?=null

    init{
        getGameList()
    }

    private fun getGameList(){
       gamePagingData= _model.getGames().cachedIn(viewModelScope)

    }
}