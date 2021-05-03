package com.zoothii.rent_car_system_android.model

data class Rental(
    val id: Int,
    var carId: Int,
    var customerId: Int,
    var rentDate: String,
    var returnDate: String,
)