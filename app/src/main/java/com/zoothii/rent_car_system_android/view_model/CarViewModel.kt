package com.zoothii.rent_car_system_android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response_model.DataResponseModel
import com.zoothii.rent_car_system_android.repository.CarRepository
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    fun getAllCarsDetailsWithPreviewFirstImage(): MutableLiveData<DataResponseModel<CarDetail>> {
        val mutableLiveData = MutableLiveData<DataResponseModel<CarDetail>>()
        viewModelScope.launch {
            val response = repository.getAllCarsDetailsWithPreviewFirstImage()
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