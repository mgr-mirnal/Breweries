package com.example.brewery.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewery.common.ResponseState
import com.example.brewery.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

const val TAG = "ViewModel"
@HiltViewModel
class BreweryViewModel @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
        // Using viewModelScope with our exception handler
        private val viewModelSafeScope by lazy {
            viewModelScope + coroutineExceptionHandler
        }
    private val coroutineExceptionHandler by lazy {
            CoroutineExceptionHandler { coroutineContext, throwable ->
                Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}", throwable
                )

            }
        }
        private val _breweryResponse = MutableLiveData<ResponseState>()
        val breweryResponse: LiveData<ResponseState> get() = _breweryResponse


        fun getBreweryList() {
            viewModelSafeScope.launch(ioDispatcher) {
                repository.getBrewery().collect {
                    _breweryResponse.postValue(it)
                }
            }
        }
    fun setLoadingState(){_breweryResponse.value = ResponseState.Loading}

}
