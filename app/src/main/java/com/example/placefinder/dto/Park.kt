package com.example.placefinder.dto

data class Park (var stateCode: String, var parkName: String) {

    override fun toString(): String {
        return "$parkName $stateCode"
    }

}