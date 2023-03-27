package com.example.placefinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placefinder.dto.Address
import com.example.placefinder.service.ParkService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var parks = MutableLiveData<List<Address>?>()
    var parkService : ParkService = ParkService()

    fun fetchParks() {
        viewModelScope.launch {
            var innerPark = parkService.fetchParks()
            parks.postValue(innerPark)
        }
    }

}