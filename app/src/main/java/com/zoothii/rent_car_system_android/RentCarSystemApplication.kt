package com.zoothii.rent_car_system_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RentCarSystemApplication : Application() {
    // TODO needed for dependency injection viewModels in fragments
    // TODO add 'android:name=".RentCarSystemApplication"' inside AndroidManifest
}