package com.zoothii.rent_car_system_android.model

data class CustomerResponse(
    val id: Int,
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val eMail: String,
    val companyName: String,
    val accessToken: AccessToken,
)