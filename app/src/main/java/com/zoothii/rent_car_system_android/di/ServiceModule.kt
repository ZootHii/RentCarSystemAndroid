package com.zoothii.rent_car_system_android.di

import com.zoothii.rent_car_system_android.remote.RetrofitService
import com.zoothii.rent_car_system_android.remote.service.IAuthsService
import com.zoothii.rent_car_system_android.remote.service.ICarImagesService
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.remote.service.IRentalsService
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
    fun provideCarsService(): ICarsService {
        val service: ICarsService by lazy {
            RetrofitService.buildService(ICarsService::class.java)
        }
        return service
    }

    @Singleton
    @Provides
    fun provideCarImagesService(): ICarImagesService {
        val service: ICarImagesService by lazy {
            RetrofitService.buildService(ICarImagesService::class.java)
        }
        return service
    }

    @Singleton
    @Provides
    fun provideRentalsService(): IRentalsService {
        val service: IRentalsService by lazy {
            RetrofitService.buildService(IRentalsService::class.java)
        }
        return service
    }

    @Singleton
    @Provides
    fun provideAuthsService(): IAuthsService {
        val service: IAuthsService by lazy {
            RetrofitService.buildService(IAuthsService::class.java)
        }
        return service
    }
}