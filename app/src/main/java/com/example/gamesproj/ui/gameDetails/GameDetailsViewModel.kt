package com.example.gamesproj.ui.gameDetails

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesproj.R
import com.example.gamesproj.model.ModelRepo
import com.example.gamesproj.model.ModelRepository
import com.example.gamesproj.model.dataClasses.ErrorResponse
import com.example.gamesproj.model.dataClasses.GameDetails
import com.example.gamesproj.utils.Event
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GameDetailsViewModel (private val _model: ModelRepo) : ViewModel() {
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

    private val _errorViewVisibility = MutableLiveData<Int?>()

    val errorViewVisibility: LiveData<Int?>
        get() = _errorViewVisibility

    private val _gameNameText = MutableLiveData<String?>()

    val gameNameText: LiveData<String?>
        get() = _gameNameText

    private val _gameDateText = MutableLiveData<String?>()

    val gameDateText: LiveData<String?>
        get() = _gameDateText

    private val _gameDescreptionText = MutableLiveData<String?>()

    val gameDescreptionText: LiveData<String?>
        get() = _gameDescreptionText

    private val _imageUrl = MutableLiveData<String?>()

    val imageUrl: LiveData<String?>
        get() = _imageUrl


    private var gameId=-1

    init {
        showLoading(true)
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
        showLoading(true)
        fetchGameDetailsApi(gameId)
    }

    /**
     * Process args and if there is issue in args just go back.
     */
    fun gotArgs(id:Int) {
        Timber.v("gotArgs$gameId")
        gameId=id
        fetchGameDetailsApi(gameId)
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

    /**
     * Switch to main dispatcher and set _errorMessageId or _errorMessage based on what is available
     * then set _errorViewVisibility for displaying error view.
     */
    private fun showErrorView(errorMessageId: Int? = null, errorMessage: String? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorMessageId.value = errorMessageId
            _errorMessage.value = errorMessage
            showErrorView(true)
        }
    }

    private fun fetchGameDetailsApi(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = _model.getGameDetails(id)) {
                is NetworkResponse.Success -> handleRetreiveListResponse(
                    response.code,
                    response.body
                )

                is NetworkResponse.ServerError -> when {
                    response.body != null && response.body is ErrorResponse ->
                        handleRetreiveListErrorResponse(
                            response.code,
                            response.body as ErrorResponse
                        )

                    else -> handleRetreiveListError()
                }

                else -> handleRetreiveListError()
            }
        }
    }
    private fun handleRetreiveListResponse(statusCode: Int, response: GameDetails) {
        Timber.v("handleGameDetailsResponse - statusCode: $statusCode")
        showForm(true)
        viewModelScope.launch(Dispatchers.Main) {
            _imageUrl.value = response.background_image
            _gameNameText.value=response.name
            _gameDescreptionText.value=
                response.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() }
            _gameDateText.value=response.released
        }
    }

    private fun handleRetreiveListErrorResponse(statusCode: Int, response: ErrorResponse) {
        Timber.v("handleGameDetailsErrorResponse - statusCode: $statusCode")
        showForm(false)
        showErrorView(true)
        when (statusCode) {
            0, 500 -> showErrorView(errorMessageId = R.string.connection_error)
            else -> when (response.error) {
                null -> showErrorView(errorMessageId = R.string.list_not_found)
                else -> showErrorView(errorMessage = response.error)
            }
        }
    }

    private fun handleRetreiveListError() {
        Timber.v("handleGameDetailsError")
        showForm(false)
        showErrorView(true)
        showErrorView(errorMessageId = R.string.list_not_found)
    }
}