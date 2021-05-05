package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICarImagesService {

    @GET("CarImages/get/all")
    suspend fun getAllCarImages(): Response<DataResponseModel<CarImage>>

    @GET("CarImages/get/all/by/car/id")
    suspend fun getCarImagesByCarId(@Query("carId") carId: Int): Response<DataResponseModel<CarImage>>
}