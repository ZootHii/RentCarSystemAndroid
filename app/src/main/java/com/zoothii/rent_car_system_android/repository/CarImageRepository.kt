package com.zoothii.rent_car_system_android.repository

import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import retrofit2.Response

class CarImageRepository(private val carImagesService: ICarImagesService) {

    suspend fun getAllCarImages(): Response<DataResponseModel<CarImage>> =
        carImagesService.getAllCarImages()

    suspend fun getCarImagesByCarId(carId: Int): Response<DataResponseModel<CarImage>> =
        carImagesService.getCarImagesByCarId(carId)
}