package com.dannytest.animal.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Callback
import java.util.*


interface ApiInterface {

    @GET("AnimalRecognition")
    suspend fun getData(callback: Callback<Dictionary<String, Any>>)}

