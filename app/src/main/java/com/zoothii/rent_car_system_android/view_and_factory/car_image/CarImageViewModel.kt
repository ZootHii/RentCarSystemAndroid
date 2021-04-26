package com.zoothii.rent_car_system_android.view_and_factory.car_image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.model.response.DataResponseModel
import com.zoothii.rent_car_system_android.model.response.ResponseModel
import com.zoothii.rent_car_system_android.model.response.SingleDataResponseModel
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.repository.CarRepository
import kotlinx.coroutines.launch

class CarImageViewModel (private val repository: CarImageRepository) : ViewModel() {

    lateinit var carImageDataResponse: MutableLiveData<DataResponseModel<CarImage>>
    lateinit var carImageSingleDataResponse: MutableLiveData<SingleDataResponseModel<CarImage>>
    lateinit var carResponse: MutableLiveData<ResponseModel>

    fun getAllCarImages() {
        viewModelScope.launch {
            carImageDataResponse = repository.getAllCarImages()
        }
    }

    /*fun getCarById(id: Int) {
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

    fun getAllCarsDetails() {
        viewModelScope.launch {
            carDetailDataResponse = repository.getAllCarsDetails()
        }
    }*/

    fun getCarImagesByCarId(carId: Int): MutableLiveData<DataResponseModel<CarImage>>{
        viewModelScope.launch {
            carImageDataResponse = repository.getCarImagesByCarId(carId)
        }
        return carImageDataResponse
    }

    /*fun getCarPreviewFirstImageByCarId(carId: Int){ // TODO no need backend modification has been made
        viewModelScope.launch {
            carImageSingleDataResponse = repository.getCarPreviewFirstImageByCarId(carId)
        }
    }*/


}