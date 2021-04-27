package com.zoothii.rent_car_system_android.di

import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCarRepository(carService: ICarsService): CarRepository { // todo put another class for repositories
        return CarRepository(carService)
    }

    @Singleton
    @Provides
    fun provideCarImageRepository(carImagesService: ICarImagesService): CarImageRepository { // todo put another class for repositories
        return CarImageRepository(carImagesService)
    }
}