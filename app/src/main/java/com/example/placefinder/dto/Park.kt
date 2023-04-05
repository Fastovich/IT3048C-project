package com.example.placefinder.dto

    data class Park (
        val total: String = "",
        val limit: String = "",
        val start: String = "",
        val data: List<Datum>
    ) {

        data class Datum(
            val id: String = "",
            val url: String = "",
            val fullName: String = "",
            val parkCode: String = "",
            val description: String = "",
            val latitude: String = "",
            val longitude: String = "",
            val latLong: String = "",
            val activities: List<Activity>,
            val topics: List<Activity>,
            val states: String = "",
            val contacts: Contacts,
            val entranceFees: List<Entrance>,
            val entrancePasses: List<Entrance>,
            val fees: List<Entrance>,
            val directionsInfo: String = "",
            val directionsURL: String = "",
            val operatingHours: List<OperatingHour>,
            val addresses: List<Address>,
            val images: List<Image>,
            val weatherInfo: String = "",
            val name: String = "",
            val designation: String = ""
        ){
           data class Activity (
            val id: String = "",
            val name: String = ""
        )
        data class Address (
            val postalCode: String = "",
            val city: String = "",
            val stateCode: String = "",
            val line1: String = "",
            val type: String = "",
            val line3: String = "",
            val line2: String = ""
            )
        data class Contacts (
            val phoneNumbers: List<PhoneNumber>,
            val emailAddresses: List<EmailAddress>
        )
        data class EmailAddress (
            val description: String = "",
            val emailAddress: String = ""
        )
       data class PhoneNumber (
            val phoneNumber: String = "",
            val description: String = "",
            val extension: String = "",
            val type: String = ""
            )
        data class Entrance (
            val cost: String = "",
            val description: String = "",
            val title: String = ""
        )
        data class Image (
            val credit: String = "",
            val title: String = "",
            val altText: String = "",
            val caption: String = "",
            val url: String = ""
        )
        data class OperatingHour (
            val exceptions: List<Exception>,
            val description: String = "",
            val standardHours: Exception.Hours,
            val name: String = ""
            ) {
               data class Exception(
                    val exceptionHours: Hours,
                    val startDate: String = "",
                    val name: String = "",
                    val endDate: String = ""
                ){
                   data class Hours (
                       val wednesday: String = "",
                       val monday: String = "",
                       val thursday: String = "",
                       val sunday: String = "",
                       val tuesday: String = "",
                       val friday: String = "",
                       val saturday: String = ""
                   )
               }
            }
        }
    }
