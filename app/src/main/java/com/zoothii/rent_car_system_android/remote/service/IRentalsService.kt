package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.model.response_model.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IRentalsService {

    @POST("Rentals/check/if/car/can/be/rented")
    suspend fun checkIfCarCanBeRented(@Body rental: Rental): Response<ResponseModel>
}