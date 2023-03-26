package com.example.placefinder.dto

data class Park (
    val total: String,
    val limit: String,
    val start: String,
    val data: List<Datum>
)

data class Datum (
    val id: String? = null,
    val url: String? = null,
    val fullName: String? = null,
    val parkCode: String? = null,
    val description: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val latLong: String? = null,
    val activities: List<Activity>? = null,
    val topics: List<Activity>? = null,
    val states: String? = null,
    val contacts: Contacts? = null,
    val entranceFees: List<Entrance>? = null,
    val entrancePasses: List<Entrance>? = null,
    val fees: List<Any?>? = null,
    val directionsInfo: String? = null,
    val directionsURL: String? = null,
    val operatingHours: List<OperatingHour>? = null,
    val addresses: List<Address>? = null,
    val images: List<Image>? = null,
    val weatherInfo: String? = null,
    val name: String? = null,
    val designation: String? = null
)

data class Activity (
    val id: String,
    val name: String
)

data class Address (
    val postalCode: String? = null,
    val city: String,
    val stateCode: String,
    val line1: String? = null,
    val type: AddressType? = null,
    val line3: String? = null,
    val line2: String? = null
)

enum class AddressType {
    Mailing,
    Physical
}

data class Contacts (
    val phoneNumbers: List<PhoneNumber>,
    val emailAddresses: List<EmailAddress>
)

data class EmailAddress (
    val description: String,
    val emailAddress: String
)

data class PhoneNumber (
    val phoneNumber: String,
    val description: String,
    val extension: String,
    val type: PhoneNumberType
)

enum class PhoneNumberType {
    Fax,
    TTY,
    Voice
}

data class Entrance (
    val cost: String,
    val description: String,
    val title: String
)

data class Image (
    val credit: String,
    val title: String,
    val altText: String,
    val caption: String,
    val url: String
)

data class OperatingHour (
    val exceptions: List<Exception>,
    val description: String,
    val standardHours: Hours,
    val name: String
)

data class Exception (
    val exceptionHours: Hours,
    val startDate: String,
    val name: String,
    val endDate: String
)

data class Hours (
    val wednesday: String? = null,
    val monday: String? = null,
    val thursday: String? = null,
    val sunday: String? = null,
    val tuesday: String? = null,
    val friday: String? = null,
    val saturday: String? = null
)
