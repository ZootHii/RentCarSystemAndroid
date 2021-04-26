package com.zoothii.rent_car_system_android.di

import com.zoothii.rent_car_system_android.remote.RetrofitService
import com.zoothii.rent_car_system_android.remote.service.ICarsService
import com.zoothii.rent_car_system_android.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCarService(): ICarsService { // todo put another class for services
        val service: ICarsService by lazy {
            RetrofitService.buildService(ICarsService::class.java)
        }
        return service
    }

    @Singleton
    @Provides
    fun provideCarRepository(carService: ICarsService): CarRepository { // todo put another class for repositories
        return CarRepository(carService)
    }

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob()) // superviserjob coroutine fail olursa childların fail olmasını önlüyor

}
