package com.dannytest.animal.model

import java.io.Serializable

data class PetsItem(
    val album_file: String,
    val animal_age: String,
    val animal_bacterin: String,
    val animal_bodytype: String,
    val animal_colour: String,
    val animal_foundplace: String,
    val animal_id: Int,
    val animal_kind: String,
    val animal_subid:String,
    val animal_opendate: String,
    val animal_sex: String,
    val shelter_address: String,
    val shelter_name: String,
    val cDate : String,
    val shelter_tel: String
) : Serializable