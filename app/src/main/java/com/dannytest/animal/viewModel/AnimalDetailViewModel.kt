package com.dannytest.animal.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dannytest.animal.model.PetsItem

class AnimalDetailViewModel : ViewModel() {
    val For_Detail : MutableLiveData<PetsItem> by lazy {MutableLiveData<PetsItem>()}
}