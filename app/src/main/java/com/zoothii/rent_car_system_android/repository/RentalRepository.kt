package com.zoothii.rent_car_system_android.repository

import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.model.response_model.ResponseModel
import com.zoothii.rent_car_system_android.remote.service.IRentalsService
import retrofit2.Response

class RentalRepository(private val service: IRentalsService) {

    suspend fun checkIfCarCanBeRented(rental: Rental): Response<ResponseModel> =
        service.checkIfCarCanBeRented(rental)
}