package com.zoothii.rent_car_system_android.model

data class CarDetail(
    val id: Int,
    val brandId: Int,
    val colorId: Int,
    val modelYear: String,
    val dailyPrice: Double,
    val description: String,
    val brandName: String,
    val colorName: String,
    var previewCarImage: String,
)