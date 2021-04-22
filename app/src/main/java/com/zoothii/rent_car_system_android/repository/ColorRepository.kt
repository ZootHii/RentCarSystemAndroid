package com.zoothii.rent_car_system_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoothii.rent_car_system_android.model.Color
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.RetrofitService
import com.zoothii.rent_car_system_android.remote.service.IColorsService
import com.zoothii.rent_car_system_android.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ColorRepository {

    companion object Factory {
        fun create(): ColorRepository = ColorRepository()
    }

    private val colorService: IColorsService by lazy {
        RetrofitService.buildService(IColorsService::class.java)
    }

    val colorDataResponse: MutableLiveData<DataResponseModel<Color>> = MutableLiveData()
    val colorSingleDataResponse: MutableLiveData<SingleDataResponseModel<Color>> = MutableLiveData()
    val colorResponse: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getAllColors(): MutableLiveData<DataResponseModel<Color>> {
        colorService.getAllColors().enqueue(object : Callback<DataResponseModel<Color>> {
            override fun onResponse(
                call: Call<DataResponseModel<Color>>,
                response: Response<DataResponseModel<Color>>
            ) {
                if (response.isSuccessful) {
                    colorDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<Color>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return colorDataResponse
    }

    fun getColorById(id: Int): MutableLiveData<SingleDataResponseModel<Color>> {
        colorService.getColorById(id).enqueue(object : Callback<SingleDataResponseModel<Color>> {
            override fun onResponse(
                call: Call<SingleDataResponseModel<Color>>,
                response: Response<SingleDataResponseModel<Color>>
            ) {
                if (response.isSuccessful) {
                    colorSingleDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<SingleDataResponseModel<Color>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return colorSingleDataResponse
    }

    fun addColor(color: Color): MutableLiveData<ResponseModel> {
        colorService.addColor(color).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    colorResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return colorResponse
    }

    fun updateColor(color: Color): MutableLiveData<ResponseModel> {
        colorService.updateColor(color).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    colorResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return colorResponse
    }

    fun deleteColor(color: Color): MutableLiveData<ResponseModel> {
        colorService.deleteColor(color).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    colorResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return colorResponse
    }
}