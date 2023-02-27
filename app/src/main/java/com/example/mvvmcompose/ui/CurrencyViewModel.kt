package com.example.mvvmcompose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcompose.data.coincap.Coincap
import com.example.mvvmcompose.data.remote.ApiDetail
import com.example.mvvmcompose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val apiDetail: ApiDetail
): ViewModel() {

    private val _currencyList = MutableLiveData<NetworkResult<Coincap>>()
    val currencyList
        get() = _currencyList

    init { getCurrencyList() }

    fun getCurrencyList() {
        viewModelScope.launch {
            _currencyList.value = NetworkResult.Loading()
            val result = apiDetail.getCoins()
            if (result.isSuccessful)
                _currencyList.value = NetworkResult.Success(result.body())
            else
                _currencyList.value = NetworkResult.Error(result.body(), result.message())
        }
    }
}