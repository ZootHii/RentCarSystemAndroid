package com.zoothii.rent_car_system_android.view_and_factory.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    lateinit var carDataResponse: MutableLiveData<DataResponseModel<Car>>
    private lateinit var carDetailDataResponse: MutableLiveData<DataResponseModel<CarDetail>>
    lateinit var carDetailSingleDataResponse: MutableLiveData<SingleDataResponseModel<CarDetail>>
    lateinit var carSingleDataResponse: MutableLiveData<SingleDataResponseModel<Car>>
    lateinit var carResponse: MutableLiveData<ResponseModel>

    fun getAllCars(): MutableLiveData<DataResponseModel<Car>> {
        viewModelScope.launch {
            carDataResponse = repository.getAllCars()
        }
        return carDataResponse
    }

/*
    fun getCarById(id: Int) {
        viewModelScope.launch {
            carSingleDataResponse = repository.getCarById(id)
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch {
            carResponse = repository.addCar(car)
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            carResponse = repository.updateCar(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch {
            carResponse = repository.deleteCar(car)
        }
    }

    fun getCarsByColorId(colorId: Int) {
        viewModelScope.launch {
            carDataResponse = repository.getCarsByColorId(colorId)
        }
    }

    fun getCarsByBrandId(brandId: Int) {
        viewModelScope.launch {
            carDataResponse = repository.getCarsByBrandId(brandId)
        }
    }
*/

    fun getAllCarsDetails(): MutableLiveData<DataResponseModel<CarDetail>> {
        viewModelScope.launch {
            carDetailDataResponse = repository.getAllCarsDetails()
        }
        return carDetailDataResponse
    }

    fun getAllCarsDetailsWithPreviewFirstImage(): MutableLiveData<DataResponseModel<CarDetail>> {
        viewModelScope.launch {
            carDetailDataResponse = repository.getAllCarsDetailsWithPreviewFirstImage()
        }
        return carDetailDataResponse
    }
}