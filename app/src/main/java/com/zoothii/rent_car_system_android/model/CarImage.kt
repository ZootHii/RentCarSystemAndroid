package com.zoothii.rent_car_system_android.model

data class CarImage(
    val guid: String,
    val id: Int,
    val carId: Int,
    val imageName: String,
    val uploadDate: String,
    val imagePath: String,
)