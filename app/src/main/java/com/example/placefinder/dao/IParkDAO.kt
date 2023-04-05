package com.example.placefinder.dao

import com.example.placefinder.dto.Park
import retrofit2.Call
import retrofit2.http.GET

interface IParkDAO {


    @GET("/api/v1/parks?api_key=tnhVdXhQTib85jp4ey64DRKoe3r8jZN7RtFTYrjM")
    fun getAllParks() : Call<List<Park.Datum.Address>>
}