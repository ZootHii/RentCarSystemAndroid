package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ICarsService {

    @GET("Cars/get/all")
    fun getAllCars(): Call<DataResponseModel<Car>>

    @GET("Cars/get/by/id")
    fun getCarById(@Query("id") id: Int): Call<SingleDataResponseModel<Car>>

    @POST("Cars/add")
    fun addCar(@Body car: Car): Call<ResponseModel>

    @POST("Cars/update")
    fun updateCar(@Body car: Car): Call<ResponseModel>

    @POST("Cars/delete")
    fun deleteCar(@Body car: Car): Call<ResponseModel>

    @GET("Cars/get/all/by/color/id")
    fun getCarsByColorId(@Query("colorId") colorId: Int): Call<DataResponseModel<Car>>

    @GET("Cars/get/all/by/brand/id")
    fun getCarsByBrandId(@Query("brandId") brandId: Int): Call<DataResponseModel<Car>>

    @GET("Cars/get/all/details")
    fun getAllCarsDetails(): Call<DataResponseModel<CarDetail>>

    @GET("Cars/get/all/details/with/preview/first/image")
    fun getAllCarsDetailsWithPreviewFirstImage(): Call<DataResponseModel<CarDetail>>

    @GET("Cars/get/all/details/by/color/id")
    fun getCarsDetailsByColorId(@Query("colorId") colorId: Int): Call<DataResponseModel<CarDetail>>

    @GET("Cars/get/all/details/by/brand/id")
    fun getCarsDetailsByBrandId(@Query("brandId") brandId: Int): Call<DataResponseModel<CarDetail>>

    @GET("Cars/get/details/by/car/id")
    fun getCarDetailsByCarId(@Query("carId") carId: Int): Call<SingleDataResponseModel<CarDetail>>

}