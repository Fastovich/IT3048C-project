package com.example.placefinder.service

import com.example.placefinder.RetrofitClientInstance
import com.example.placefinder.dao.IParkDAO
import com.example.placefinder.dto.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface IParkService {
    suspend fun fetchParks(): List<Address>?
}
class ParkService: IParkService {
    override suspend fun fetchParks(): List<Address>? {

        return withContext(Dispatchers.IO) {
            val retrofit = RetrofitClientInstance.retrofitInstance?.create(IParkDAO::class.java)
            val parks = async { retrofit?.getAllParks() }

            return@withContext parks.await()?.awaitResponse()?.body()
        }
    }
}