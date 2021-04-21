package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ICarImagesService {

    @GET("CarImages/get/all")
    fun getAllCarImages(): Call<DataResponseModel<CarImage>>

    @GET("CarImages/get/by/id")
    fun getCarImageById(@Query("id") id: Int): Call<SingleDataResponseModel<CarImage>>

    @POST("CarImages/add")
    fun addCarImage(@Body carImage: CarImage): Call<ResponseModel>

    @POST("CarImages/update")
    fun updateCarImage(@Body carImage: CarImage): Call<ResponseModel>

    @POST("CarImages/delete")
    fun deleteCarImage(@Body carImage: CarImage): Call<ResponseModel>

    @GET("CarImages/get/all/by/car/id")
    fun getCarImagesByCarId(@Query("carId") carId: Int): Call<DataResponseModel<CarImage>>

    @GET("CarImages/get/preview/first/image/by/car/id")
    fun getCarPreviewFirstImageByCarId(@Query("carId") carId: Int): Call<SingleDataResponseModel<CarImage>>

}