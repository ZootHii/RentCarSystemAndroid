package com.zoothii.rent_car_system_android.di

import com.zoothii.rent_car_system_android.remote.service.IAuthsService
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.remote.service.IRentalsService
import com.zoothii.rent_car_system_android.repository.AuthRepository
import com.zoothii.rent_car_system_android.repository.CarImageRepository
import com.zoothii.rent_car_system_android.repository.CarRepository
import com.zoothii.rent_car_system_android.repository.RentalRepository
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
    fun provideCarRepository(carService: ICarsService): CarRepository {
        return CarRepository(carService)
    }

    @Singleton
    @Provides
    fun provideCarImageRepository(carImagesService: ICarImagesService): CarImageRepository {
        return CarImageRepository(carImagesService)
    }

    @Singleton
    @Provides
    fun provideRentalRepository(rentalsService: IRentalsService): RentalRepository {
        return RentalRepository(rentalsService)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authsService: IAuthsService): AuthRepository {
        return AuthRepository(authsService)
    }
}