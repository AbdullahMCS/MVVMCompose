package com.example.mvvmcompose.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcompose.data.dogs.Dog
import com.example.mvvmcompose.data.ducks.Duck
import com.example.mvvmcompose.data.remote.ApiDetail
import com.example.mvvmcompose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DucksViewModel @Inject constructor(
    private val apiDetail: ApiDetail
): ViewModel() {

    private val _duck = MutableLiveData<NetworkResult<Duck>>()
    val duck
        get() = _duck

    private val _dog = MutableLiveData<NetworkResult<Dog>>()
    val dog
        get() = _dog

    init {
        getDuck()
        getDog()
    }

    private fun getDuck() {
        viewModelScope.launch {
            _duck.value = NetworkResult.Loading()
            val result = apiDetail.getDuck()
            if (result.isSuccessful)
                _duck.value = NetworkResult.Success(result.body())
            else
                _duck.value = NetworkResult.Error(result.body(), result.message())
        }
    }

    private fun getDog() {
        viewModelScope.launch {
            _dog.value = NetworkResult.Loading()
            val result = apiDetail.getDog()
            if (result.isSuccessful)
                _dog.value = NetworkResult.Success(result.body())
            else
                _dog.value = NetworkResult.Error(result.body(), result.message())
        }
    }
}