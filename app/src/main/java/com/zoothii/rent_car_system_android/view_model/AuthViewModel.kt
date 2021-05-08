package com.zoothii.rent_car_system_android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.CustomerResponse
import com.zoothii.rent_car_system_android.model.UserLogin
import com.zoothii.rent_car_system_android.model.response_model.SingleDataResponseModel
import com.zoothii.rent_car_system_android.repository.AuthRepository
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    fun loginCustomer(userLogin: UserLogin): MutableLiveData<SingleDataResponseModel<CustomerResponse>> {
        val mutableLiveData = MutableLiveData<SingleDataResponseModel<CustomerResponse>>()
        viewModelScope.launch {
            val response = repository.loginCustomer(userLogin)
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