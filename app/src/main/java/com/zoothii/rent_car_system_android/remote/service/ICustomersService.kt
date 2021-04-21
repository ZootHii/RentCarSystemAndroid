package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.Customer
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ICustomersService {

    @GET("Customers/get/all")
    fun getAllCustomers(): Call<DataResponseModel<Customer>>

    @GET("Customers/get/by/id")
    fun getCustomerById(@Query("id") id: Int): Call<SingleDataResponseModel<Customer>>

    @POST("Customers/add")
    fun addCustomer(@Body customer: Customer): Call<ResponseModel>

    @POST("Customers/update")
    fun updateCustomer(@Body customer: Customer): Call<ResponseModel>

    @POST("Customers/delete")
    fun deleteCustomer(@Body customer: Customer): Call<ResponseModel>

    @GET("Customers/get/all/details")
    fun getCustomersDetails(@Body customer: Customer): Call<DataResponseModel<Customer>>

    @GET("Customers/get/by/user/id")
    fun getCustomerByUserId(@Query("userId") userId: Int): Call<SingleDataResponseModel<Customer>>


}