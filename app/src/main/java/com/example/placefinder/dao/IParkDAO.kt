package com.example.placefinder.dao

import com.example.placefinder.dto.Park
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IParkDAO {


    @GET("/api/v1/parks?")
    fun getAllParks(@Query("api_key")apiKey: String = "tnhVdXhQTib85jp4ey64DRKoe3r8jZN7RtFTYrjM") : Call<Park.Datum.Address>
}
