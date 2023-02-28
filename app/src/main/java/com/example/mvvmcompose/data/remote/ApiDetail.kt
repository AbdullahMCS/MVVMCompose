package com.example.mvvmcompose.data.remote

import com.example.mvvmcompose.data.dogs.Dog
import com.example.mvvmcompose.data.ducks.Duck
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiDetail {

    @GET//(ApiReference.RANDOM)
    suspend fun getDuck(
        @Url url: String = ApiReference.BASE_URL_DUCK
    ): Response<Duck>

    @GET//(ApiReference.RANDOM)
    suspend fun getDog(
        @Url url: String = ApiReference.BASE_URL_DOG
    ): Response<Dog>
}