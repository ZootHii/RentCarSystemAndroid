package com.zoothii.rent_car_system_android.remote

import com.zoothii.rent_car_system_android.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val okHttpClient: OkHttpClient = OkHttpClient()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}