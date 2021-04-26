package com.zoothii.rent_car_system_android.view_and_factory.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoothii.rent_car_system_android.di.AppModule
import com.zoothii.rent_car_system_android.repository.CarRepository


@Suppress("UNCHECKED_CAST")


class CarViewModelFactory(/*private val repository: CarRepository*/) : ViewModelProvider.Factory {
    //@Inject lateinit var repository: CarRepository
    private val repository: CarRepository =
        CarRepository(AppModule.provideCarService()) // TODO too hard for me to make injection for fragment - factory // -> done no need a factory anymore

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarViewModel(repository) as T
    }
}

/*
class ViewModelFactory<VM: ViewModel>(val provider: () -> VM): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  provider() as T
    }
}*/
