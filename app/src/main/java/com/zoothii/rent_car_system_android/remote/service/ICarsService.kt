package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response_model.DataResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ICarsService {

    @GET("Cars/get/all/details/with/preview/first/image")
    suspend fun getAllCarsDetailsWithPreviewFirstImage(): Response<DataResponseModel<CarDetail>>
}