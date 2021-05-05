package com.zoothii.rent_car_system_android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarImageViewModel @Inject constructor(
    private val repository: CarImageRepository
) : ViewModel() {

    fun getAllCarImages(): MutableLiveData<DataResponseModel<CarImage>> {
        val mutableLiveData = MutableLiveData<DataResponseModel<CarImage>>()
        viewModelScope.launch {
            val response = repository.getAllCarImages()
            if (response.isSuccessful) {
                response.body()?.let {
                    mutableLiveData.postValue(response.body())
                }
            } else {
                response.errorBody()?.let {
                    Helper.handleError(response.errorBody()!!.string()) // .string() turns to JSON
                }
            }
        }
        return mutableLiveData
    }

    fun getCarImagesByCarId(carId: Int): MutableLiveData<DataResponseModel<CarImage>> {
        val mutableLiveData = MutableLiveData<DataResponseModel<CarImage>>()
        viewModelScope.launch {
            val response = repository.getCarImagesByCarId(carId)
            if (response.isSuccessful) {
                response.body()?.let {
                    mutableLiveData.postValue(response.body())
                }
            } else {
                response.errorBody()?.let {
                    Helper.handleError(response.errorBody()!!.string()) // .string() turns to JSON
                }
            }
        }
        return mutableLiveData
    }
}