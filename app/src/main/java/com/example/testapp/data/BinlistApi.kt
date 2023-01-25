package com.example.testapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {
    @GET("{bin}")
    fun getCard(@Path("bin") id: String?): Call<CardDetails>
}