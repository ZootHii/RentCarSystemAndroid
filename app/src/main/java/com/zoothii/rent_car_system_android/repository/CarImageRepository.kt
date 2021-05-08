package com.zoothii.rent_car_system_android.repository

import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response_model.DataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import retrofit2.Response

class CarImageRepository(private val service: ICarImagesService) {

    suspend fun getAllCarImages(): Response<DataResponseModel<CarImage>> =
        service.getAllCarImages()

    suspend fun getCarImagesByCarId(carId: Int): Response<DataResponseModel<CarImage>> =
        service.getCarImagesByCarId(carId)
}