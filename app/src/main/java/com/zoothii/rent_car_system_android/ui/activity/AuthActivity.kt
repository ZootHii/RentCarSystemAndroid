package com.zoothii.rent_car_system_android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Helper.applicationContext = this
    }
}