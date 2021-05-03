package com.zoothii.rent_car_system_android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepository(private val carsService: ICarsService) {

    val carDetailDataResponse: MutableLiveData<DataResponseModel<CarDetail>> = MutableLiveData()
    val carDetailSingleDataResponse: MutableLiveData<SingleDataResponseModel<CarDetail>> =
        MutableLiveData()
    val carDataResponse: MutableLiveData<DataResponseModel<Car>> = MutableLiveData()
    val carSingleDataResponse: MutableLiveData<SingleDataResponseModel<Car>> = MutableLiveData()
    val carResponse: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getAllCars(): MutableLiveData<DataResponseModel<Car>> {
        carsService.getAllCars().enqueue(object : Callback<DataResponseModel<Car>> {
            override fun onResponse(
                call: Call<DataResponseModel<Car>>,
                response: Response<DataResponseModel<Car>>
            ) {
                if (response.isSuccessful) {
                    carDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<Car>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carDataResponse
    }

    fun getCarById(id: Int): MutableLiveData<SingleDataResponseModel<Car>> {
        carsService.getCarById(id).enqueue(object : Callback<SingleDataResponseModel<Car>> {
            override fun onResponse(
                call: Call<SingleDataResponseModel<Car>>,
                response: Response<SingleDataResponseModel<Car>>
            ) {
                if (response.isSuccessful) {
                    carSingleDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<SingleDataResponseModel<Car>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carSingleDataResponse
    }

    fun addCar(car: Car): MutableLiveData<ResponseModel> {
        carsService.addCar(car).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carResponse
    }

    fun updateCar(car: Car): MutableLiveData<ResponseModel> {
        carsService.updateCar(car).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carResponse
    }

    fun deleteCar(car: Car): MutableLiveData<ResponseModel> {
        carsService.deleteCar(car).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {
                    carResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carResponse
    }

    fun getCarsByColorId(colorId: Int): MutableLiveData<DataResponseModel<Car>> {
        carsService.getCarsByColorId(colorId).enqueue(object : Callback<DataResponseModel<Car>> {
            override fun onResponse(
                call: Call<DataResponseModel<Car>>,
                response: Response<DataResponseModel<Car>>
            ) {
                if (response.isSuccessful) {
                    carDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<Car>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carDataResponse
    }

    fun getCarsByBrandId(brandId: Int): MutableLiveData<DataResponseModel<Car>> {
        carsService.getCarsByBrandId(brandId).enqueue(object : Callback<DataResponseModel<Car>> {
            override fun onResponse(
                call: Call<DataResponseModel<Car>>,
                response: Response<DataResponseModel<Car>>
            ) {
                if (response.isSuccessful) {
                    carDataResponse.value = response.body();
                } else {
                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<Car>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carDataResponse
    }

    fun getAllCarsDetails(): MutableLiveData<DataResponseModel<CarDetail>> {
        carsService.getAllCarsDetails().enqueue(object : Callback<DataResponseModel<CarDetail>> {
            override fun onResponse(
                call: Call<DataResponseModel<CarDetail>>,
                response: Response<DataResponseModel<CarDetail>>
            ) {
                if (response.isSuccessful) {
                    carDetailDataResponse.value = response.body();
                } else {
                    Log.d("Error", response.message().toString())
                    Log.d("Error", response.toString())

                    Helper.handleError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<DataResponseModel<CarDetail>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return carDetailDataResponse
    }

    fun getAllCarsDetailsWithPreviewFirstImage(): MutableLiveData<DataResponseModel<CarDetail>> {
        carsService.getAllCarsDetailsWithPreviewFirstImage()
            .enqueue(object : Callback<DataResponseModel<CarDetail>> {
                override fun onResponse(
                    call: Call<DataResponseModel<CarDetail>>,
                    response: Response<DataResponseModel<CarDetail>>
                ) {
                    if (response.isSuccessful) {
                        carDetailDataResponse.value = response.body();
                    } else {
                        Log.d("Error", response.message().toString())
                        Log.d("Error", response.toString())

                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<DataResponseModel<CarDetail>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carDetailDataResponse
    }

    fun getCarsDetailsByColorId(colorId: Int): MutableLiveData<DataResponseModel<CarDetail>> {
        carsService.getCarsDetailsByColorId(colorId)
            .enqueue(object : Callback<DataResponseModel<CarDetail>> {
                override fun onResponse(
                    call: Call<DataResponseModel<CarDetail>>,
                    response: Response<DataResponseModel<CarDetail>>
                ) {
                    if (response.isSuccessful) {
                        carDetailDataResponse.value = response.body();
                    } else {
                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<DataResponseModel<CarDetail>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carDetailDataResponse
    }

    fun getCarsDetailsByBrandId(brandId: Int): MutableLiveData<DataResponseModel<CarDetail>> {
        carsService.getCarsDetailsByBrandId(brandId)
            .enqueue(object : Callback<DataResponseModel<CarDetail>> {
                override fun onResponse(
                    call: Call<DataResponseModel<CarDetail>>,
                    response: Response<DataResponseModel<CarDetail>>
                ) {
                    if (response.isSuccessful) {
                        carDetailDataResponse.value = response.body();
                    } else {
                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<DataResponseModel<CarDetail>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carDetailDataResponse
    }

    fun getCarDetailsByCarId(carId: Int): MutableLiveData<SingleDataResponseModel<CarDetail>> {
        carsService.getCarDetailsByCarId(carId)
            .enqueue(object : Callback<SingleDataResponseModel<CarDetail>> {
                override fun onResponse(
                    call: Call<SingleDataResponseModel<CarDetail>>,
                    response: Response<SingleDataResponseModel<CarDetail>>
                ) {
                    if (response.isSuccessful) {
                        carDetailSingleDataResponse.value = response.body();
                    } else {
                        Helper.handleError(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(
                    call: Call<SingleDataResponseModel<CarDetail>>,
                    t: Throwable
                ) {
                    Log.d("Failure", t.message.toString())
                }
            })
        return carDetailSingleDataResponse
    }
}