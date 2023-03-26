package com.example.placefinder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val PARK_API_URL = "https://developer.nps.gov"

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(PARK_API_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit
        }
}