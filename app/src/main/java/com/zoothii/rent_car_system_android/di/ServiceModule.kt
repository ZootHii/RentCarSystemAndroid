package com.zoothii.rent_car_system_android.di

import com.zoothii.rent_car_system_android.remote.RetrofitService
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideCarsService(): ICarsService { // todo put another class for services
        val service: ICarsService by lazy {
            RetrofitService.buildService(ICarsService::class.java)
        }
        return service
    }

    @Singleton
    @Provides
    fun provideCarImagesService(): ICarImagesService { // todo put another class for services
        val service: ICarImagesService by lazy {
            RetrofitService.buildService(ICarImagesService::class.java)
        }
        return service
    }
}