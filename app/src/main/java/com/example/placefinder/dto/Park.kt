package com.example.placefinder.dto

import com.google.gson.annotations.SerializedName

data class Park (@SerializedName("parkCode") var parkCode: String, var parkName: String) {

    override fun toString(): String {
        return "$parkName $parkCode"
    }

}