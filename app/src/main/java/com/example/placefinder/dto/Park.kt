package com.example.placefinder.dto

import com.google.gson.annotations.SerializedName

data class Park (var stateCode: String,@SerializedName("city") var cityName: String) {

    override fun toString(): String {
        return "$cityName $stateCode"
    }

}