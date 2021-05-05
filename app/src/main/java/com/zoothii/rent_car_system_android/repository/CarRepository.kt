package com.zoothii.rent_car_system_android.repository

import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import retrofit2.Response

class CarRepository(private val carsService: ICarsService) {

    suspend fun getAllCarsDetailsWithPreviewFirstImage(): Response<DataResponseModel<CarDetail>> =
        carsService.getAllCarsDetailsWithPreviewFirstImage()
}