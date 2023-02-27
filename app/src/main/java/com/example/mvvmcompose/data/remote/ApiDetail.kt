package com.example.mvvmcompose.data.remote

import com.example.mvvmcompose.data.coincap.Coincap
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetail {

    @GET(ApiReference.ASSETS)
    suspend fun getCoins(): Response<Coincap>
}