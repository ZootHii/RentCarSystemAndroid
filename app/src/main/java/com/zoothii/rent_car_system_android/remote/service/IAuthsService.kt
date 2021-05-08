package com.zoothii.rent_car_system_android.remote.service

import com.zoothii.rent_car_system_android.model.CustomerResponse
import com.zoothii.rent_car_system_android.model.UserLogin
import com.zoothii.rent_car_system_android.model.response_model.SingleDataResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthsService {

    @POST("Auths/login/customer")
    suspend fun loginCustomer(@Body userLogin: UserLogin): Response<SingleDataResponseModel<CustomerResponse>>

}