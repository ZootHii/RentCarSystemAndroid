package com.zoothii.rent_car_system_android.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoothii.rent_car_system_android.repository.CarRepository


@Suppress("UNCHECKED_CAST")
class CarViewModelFactory(private val repository: CarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarViewModel(repository) as T
    }
}