package com.zoothii.rent_car_system_android.repository

import com.zoothii.rent_car_system_android.model.CustomerResponse
import com.zoothii.rent_car_system_android.model.UserLogin
import com.zoothii.rent_car_system_android.model.response_model.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.service.IAuthsService
import retrofit2.Response

class AuthRepository(private val service: IAuthsService) {

    suspend fun loginCustomer(userLogin: UserLogin): Response<SingleDataResponseModel<CustomerResponse>> =
        service.loginCustomer(userLogin)
}