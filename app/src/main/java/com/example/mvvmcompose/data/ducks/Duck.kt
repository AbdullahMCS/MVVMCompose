package com.example.mvvmcompose.data.ducks


import com.google.gson.annotations.SerializedName

data class Duck(
    @SerializedName("message")
    val message: String?,
    @SerializedName("url")
    val url: String?
)