package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.Color
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import retrofit2.Call
import retrofit2.http.*

interface IColorsService {

    @GET("Colors/get/all")
    fun getAllColors(): Call<DataResponseModel<Color>>

    @GET("Colors/get/by/id")
    fun getColorById(@Query("id") id: Int): Call<SingleDataResponseModel<Color>>

    @POST("Colors/add")
    fun addColor(@Body color: Color): Call<ResponseModel>

    @POST("Colors/update")
    fun updateColor(@Body color: Color): Call<ResponseModel>

    @POST("Colors/delete")
    fun deleteColor(@Body color: Color): Call<ResponseModel>
}