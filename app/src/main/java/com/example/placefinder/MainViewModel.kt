package com.example.placefinder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placefinder.dto.Park
import com.example.placefinder.dto.User
import com.example.placefinder.service.IParkService
import com.example.placefinder.service.ParkService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var parks = MutableLiveData<Park.Datum.Address>()
    var parkService : IParkService = ParkService()
    var user : User? = null

    private lateinit var firestore : FirebaseFirestore

    fun fetchParks() {
        viewModelScope.launch {
            var innerPark = parkService.fetchParks()
            parks.postValue(innerPark)
        }
    }
 //setting up database for park info storage currently still implementing.
    fun savePark(parks: Park){

    }
    fun saveUser() {
        user?.let{
            user ->
            var handle = firestore.collection("users").document(user.uid).set(user)
            handle.addOnSuccessListener { Log.d("Firebase", "User saved successful") }
            handle.addOnFailureListener {Log.e("Firebase", "User save failed")}
        }
    }
}