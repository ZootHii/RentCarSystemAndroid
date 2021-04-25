package com.zoothii.rent_car_system_android.view_and_factory.car_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.repository.CarRepository


@Suppress("UNCHECKED_CAST")
class CarImageViewModelFactory(private val repository: CarImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarImageViewModel(repository) as T
    }
}