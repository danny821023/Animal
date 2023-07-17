package com.dannytest.animal.model

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.*

interface APIServer {
    @GET("")
    suspend fun getData(callback: Callback<Dictionary<String, Any>>)
}

class APIManager {
    private val apiService: APIServer

    init {
        val httpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.coa.gov.tw/api/v1/")
            .client(httpClient)
            .build()

        apiService = retrofit.create(APIServer::class.java)
    }

    suspend fun getDataFromApi(callback: Callback<Dictionary<String, Any>>) {
        apiService.getData(object : Callback<Dictionary<String, Any>> {
            override fun onResponse(
                call: Call<Dictionary<String, Any>>,
                response: Response<Dictionary<String, Any>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("dev danny 123 456", response.toString())
                } else {
                    // 在此處理回應不成功的情況
                    Log.d("dev danny 123 456 解析失敗", "")

                }
            }

            override fun onFailure(call: Call<Dictionary<String, Any>>, t: Throwable) {
                // 在此處理請求失敗的情況
                Log.d("dev danny 123 456 onFailure ", call.toString())

            }
        })

    }
}