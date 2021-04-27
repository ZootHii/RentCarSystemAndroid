package com.zoothii.rent_car_system_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import com.zoothii.rent_car_system_android.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CarImageRepository(private val carImagesService: ICarImagesService) {

    val carImageDataResponse: MutableLiveData<DataResponseModel<CarImage>> = MutableLiveData()
    val carImageSingleDataResponse: MutableLiveData<SingleDataResponseModel<CarImage>> =
        MutableLiveData()
    val carImageResponse: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getAllCarImages(): MutableLiveData<DataResponseModel<CarImage>> {
        carImagesService.getAllCarImages().enqueue(object : Callback<DataResponseModel<CarImage>> {
            override fun onResponse(
                call: Call<DataResponseModel<CarImage>>,
                response: Response<DataResponseModel<CarImage>>
            ) {
                if (response.isSuccessful) {
                    carImageDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<CarImage>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carImageDataResponse
    }

    fun getCarImageById(id: Int): MutableLiveData<SingleDataResponseModel<CarImage>> {
        carImagesService.getCarImageById(id)
            .enqueue(object : Callback<SingleDataResponseModel<CarImage>> {
                override fun onResponse(
                    call: Call<SingleDataResponseModel<CarImage>>,
                    response: Response<SingleDataResponseModel<CarImage>>
                ) {
                    if (response.isSuccessful) {
                        carImageSingleDataResponse.value = response.body();
                    } else {
                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(
                    call: Call<SingleDataResponseModel<CarImage>>,
                    t: Throwable
                ) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carImageSingleDataResponse
    }

    fun addCarImage(carImage: CarImage): MutableLiveData<ResponseModel> {
        carImagesService.addCarImage(carImage).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carImageResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carImageResponse
    }

    fun updateCarImage(carImage: CarImage): MutableLiveData<ResponseModel> {
        carImagesService.updateCarImage(carImage).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carImageResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carImageResponse
    }

    fun deleteCarImage(carImage: CarImage): MutableLiveData<ResponseModel> {
        carImagesService.deleteCarImage(carImage).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carImageResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carImageResponse
    }

    fun getCarImagesByCarId(carId: Int): MutableLiveData<DataResponseModel<CarImage>> {
        carImagesService.getCarImagesByCarId(carId)
            .enqueue(object : Callback<DataResponseModel<CarImage>> {
                override fun onResponse(
                    call: Call<DataResponseModel<CarImage>>,
                    response: Response<DataResponseModel<CarImage>>
                ) {
                    if (response.isSuccessful) {
                        carImageDataResponse.value = response.body();
                    } else {
                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<DataResponseModel<CarImage>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carImageDataResponse
    }

    fun getCarPreviewFirstImageByCarId(carId: Int): MutableLiveData<SingleDataResponseModel<CarImage>> {
        carImagesService.getCarPreviewFirstImageByCarId(carId)
            .enqueue(object : Callback<SingleDataResponseModel<CarImage>> {
                override fun onResponse(
                    call: Call<SingleDataResponseModel<CarImage>>,
                    response: Response<SingleDataResponseModel<CarImage>>
                ) {
                    if (response.isSuccessful) {
                        carImageSingleDataResponse.value = response.body();
                    } else {

                        Log.d("Error", response.toString())

                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(
                    call: Call<SingleDataResponseModel<CarImage>>,
                    t: Throwable
                ) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carImageSingleDataResponse
    }
}