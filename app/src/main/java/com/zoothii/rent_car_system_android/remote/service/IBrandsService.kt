package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.Brand
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBrandsService {

    @GET("Brands/get/all")
    fun getAllBrands(): Call<DataResponseModel<Brand>>

    @GET("Brands/get/by/id")
    fun getBrandById(@Query("id") id: Int): Call<SingleDataResponseModel<Brand>>

    @POST("Brands/add")
    fun addBrand(@Body brand: Brand): Call<ResponseModel>

    @POST("Brands/update")
    fun updateBrand(@Body brand: Brand): Call<ResponseModel>

    @POST("Brands/delete")
    fun deleteBrand(@Body brand: Brand): Call<ResponseModel>
}