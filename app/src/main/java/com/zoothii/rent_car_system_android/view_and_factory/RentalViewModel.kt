package com.zoothii.rent_car_system_android.view_and_factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.Rental
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(
    private val repository: RentalRepository
) : ViewModel() {

    lateinit var rentalResponse: MutableLiveData<ResponseModel>

    fun checkIfCarCanBeRented(rental: Rental): MutableLiveData<ResponseModel> {
        viewModelScope.launch {
            rentalResponse = repository.checkIfCarCanBeRented(rental)
        }
        return rentalResponse
    }
}