package com.example.gamesproj.ui.games

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gamesproj.model.ModelRepository
import com.example.gamesproj.model.dataClasses.GameDetails
import com.example.gamesproj.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


class GameViewModel (private val _model: ModelRepository) : ViewModel(){
    //region Variables
    private val _navigateBack = MutableLiveData<Event<Boolean?>?>()

    val navigateBack: LiveData<Event<Boolean?>?>
        get() = _navigateBack

    private val _loadingVisibility: MutableLiveData<Int?> = MutableLiveData()

    val loadingVisibility: LiveData<Int?>
        get() = _loadingVisibility

    private val _formVisibility: MutableLiveData<Int?> = MutableLiveData()

    val formVisibility: LiveData<Int?>
        get() = _formVisibility

    private val _errorMessageId = MutableLiveData<Int?>()

    val errorMessageId: LiveData<Int?>
        get() = _errorMessageId

    private val _errorMessage = MutableLiveData<String?>()

    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private val _emptyMessageId = MutableLiveData<Int?>()

    val emptyMessageId: LiveData<Int?>
        get() = _emptyMessageId

    private val _emptyMessage = MutableLiveData<String?>()

    val emptyMessage: LiveData<String?>
        get() = _emptyMessage

    private val _errorViewVisibility = MutableLiveData<Int?>()

    val errorViewVisibility: LiveData<Int?>
        get() = _errorViewVisibility

    private val _emptyViewVisibility = MutableLiveData<Int?>()

    val emptyViewVisibility: LiveData<Int?>
        get() = _emptyViewVisibility

    var list:LiveData<PagingData<GameDetails>>?=null


    init{
        showLoading(false)
        showErrorView(false)
        showEmptyView(false)
        showForm(true)
        list= _model.getGames().cachedIn(viewModelScope)
    }

    /**
     * Handle toolbar back button click.
     */
    fun backClicked() {
        Timber.v("backClicked")
        _navigateBack.value = Event(true)
    }
    /**
     * Handle error layout retry button click.
     */
    fun retryClicked() {
        Timber.v("retryClicked")
        showErrorView(false)
    }
    /**
     * Switch to main dispatcher and set the _errorViewVisibility value based on show variable.
     * If show is true then hide loading countries and register form as well.
     *
     * @param show Boolean indicating whether to show error view or not.
     */
    private fun showErrorView(show: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorViewVisibility.value = when (show) {
                true -> {
                    showLoading(false)
                    showForm(false)
                    View.VISIBLE
                }

                else -> View.GONE
            }
        }
    }

    /**
     * Switch to main dispatcher and set _emptyMessageId or _emptyMessage based on what is available
     * then set _emptyViewVisibility for displaying error view.
     */
    private fun showEmptyView(emptyMessageId: Int? = null, emptyMessage: String? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            _emptyMessageId.value = emptyMessageId
            _emptyMessage.value = emptyMessage
            showEmptyView(true)
        }
    }

    /**
     * Switch to main dispatcher and set the _emptyViewVisibility value based on show variable.
     * If show is true then hide loading form as well.
     *
     * @param show Boolean indicating whether to show empty view or not.
     */
    private fun showEmptyView(show: Boolean) {
        Timber.v("isShow$show")
        viewModelScope.launch(Dispatchers.Main) {
            _emptyViewVisibility.value = when (show) {
                true -> {
                    showLoading(false)
                    showForm(false)
                    showErrorView(false)
                    View.VISIBLE
                }

                else -> View.GONE
            }
        }
    }

    /**
     * Switch to main dispatcher and set the _loadingCountriesVisibility value
     * based on show variable.
     * If show is true then hide register form and error view as well.
     *
     * @param show Boolean indicating whether to show loading countries or not.
     */
    private fun showLoading(show: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            _loadingVisibility.value = when (show) {
                true -> {
                    showForm(false)
                    showErrorView(false)
                    View.VISIBLE
                }

                else -> View.GONE
            }
        }
    }

    /**
     * Switch to main dispatcher and set the _formVisibility value based on show variable.
     * If show is true then hide loading countries and error view as well.
     *
     * @param show Boolean indicating whether to show register form or not.
     */
    private fun showForm(show: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            _formVisibility.value = when (show) {
                true -> {
                    showLoading(false)
                    showErrorView(false)

                    View.VISIBLE
                }

                else -> View.GONE
            }
        }
    }
}