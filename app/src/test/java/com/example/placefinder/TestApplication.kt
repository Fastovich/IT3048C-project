package com.example.placefinder

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Set up Firebase configuration programmatically
        val options = FirebaseOptions.Builder()
            .setApplicationId("1:724319716457:android:b7eb966832c1736c1f8ef8") // Replace with your actual Firebase Application ID
            .setApiKey("AIzaSyAnKC2hyZL0nHYTkSbcgS4vBhylZilonmw") // Replace with your actual Firebase API Key
            .setDatabaseUrl("https://placefinder-87435.firebaseio.com") // Replace with your actual Firebase Database URL
            .setProjectId("placefinder-87435") // Replace with your actual Firebase Project ID
            .build()

        FirebaseApp.initializeApp(this, options)
    }
}