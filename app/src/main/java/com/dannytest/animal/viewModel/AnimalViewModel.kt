package com.dannytest.animal.viewModel

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dannytest.animal.model.PetOne
import com.dannytest.animal.model.PetsItem
import com.google.gson.Gson
import android.widget.SearchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.http.GET
import java.io.IOException
import java.util.*

class AnimalViewModel : ViewModel() {

    private var list = mutableListOf<PetsItem>()
    var _petsItemList = MutableLiveData<List<PetsItem>>()

    init {
        fetchDataFromApi()

       /* loadPet()*/
    }

  fun search (newText: String?){
        if (newText == null || newText.isEmpty()) {
            _petsItemList.value = list
        } else {
            val searchPetList = mutableListOf<PetsItem>()
            list.forEach { petsitem ->
                if (petsitem.animal_bodytype.contains(newText, true)||
                    petsitem.animal_colour.contains(newText, true) ||
                    petsitem.animal_age.contains(newText, true)
                ) {
                    searchPetList .add(petsitem)
                }
            }
            _petsItemList.value = searchPetList
        }
    }



    fun fetchDataFromApi() {
        Log.d("dev start api", "start")

        val request = Request.Builder()
            .url("https://data.coa.gov.tw/api/v1/AnimalRecognition")
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // 處理請求失敗的情況
                // ...

                Log.d("dev start fail", e.toString())
            }

            override fun onResponse(call: Call , response: Response) {
                // 處理請求成功的情況
                Log.d("AnimalViewModel", "API 請求成功")
                if (response.isSuccessful) {
                    val responseData = response.body?.string()?:""
                    Log.d("AnimalViewModel 122", responseData)
                    if (!responseData.isNullOrEmpty()) {
                        // 使用 Gson 解析 JSON 資料
                        val gson = Gson()
                        val petOne = gson.fromJson(responseData, PetOne::class.java)
                        _petsItemList.postValue(petOne.Data)
                        Log.d("dev 132", petOne.toString())
                    } else {
                        // 處理回應為空的情況
                    }
                } else {
                    // 處理請求失敗的情況
                    // ...
                }
            }
        })

    }

 /*   private fun loadPet() {
        val petSearchList = mutableListOf<PetsItem>()
        val colourToSearch = "顏色"
        val bodyTypeToSearch = "體型"
        val ageToSearch = "年紀"

        for (pet in list) {
            if (pet.animal_age == ageToSearch ||
                pet.animal_colour == colourToSearch ||
                pet.animal_bodytype == bodyTypeToSearch
            ) {
                petSearchList.add(pet)
            }
        }

        this.list = petSearchList
        this._petsItemList.value = this.list

    }*/
}