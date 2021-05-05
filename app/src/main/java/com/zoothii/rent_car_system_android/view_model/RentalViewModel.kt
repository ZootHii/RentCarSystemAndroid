package com.zoothii.rent_car_system_android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.repository.RentalRepository
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(
    private val repository: RentalRepository
) : ViewModel() {

    fun checkIfCarCanBeRented(rental: Rental): MutableLiveData<ResponseModel> {
        val mutableLiveData = MutableLiveData<ResponseModel>()
        viewModelScope.launch {
            val response = repository.checkIfCarCanBeRented(rental)
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