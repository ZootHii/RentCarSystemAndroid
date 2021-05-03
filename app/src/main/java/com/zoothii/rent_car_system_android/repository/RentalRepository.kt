package com.zoothii.rent_car_system_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.remote.service.IRentalsService
import com.zoothii.rent_car_system_android.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentalRepository(private val rentalsService: IRentalsService) {

    val rentalResponse: MutableLiveData<ResponseModel> = MutableLiveData()

    fun checkIfCarCanBeRented(rental: Rental): MutableLiveData<ResponseModel> {
        rentalsService.checkIfCarCanBeRented(rental).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    rentalResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return rentalResponse
    }
}