package com.zoothii.rent_car_system_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper


class MainActivity : AppCompatActivity() {
    inline fun <reified T: Any> Any.cast(): T{
        return this as T
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        

        val navView: BottomNavigationView = findViewById(R.id.nav_view)



        /*val repo = CarRepository()
        val view = CarViewModel(repo)

        view.getCarById(1)
        view.carSingleDataResponse.observe(this, Observer { response ->
            if (response.success){
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
                *//*for (car in response.data){
                    Log.d("Response CAR", car.toString())
                }*//*
            }
            else
            {
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
            }
        })*/



/*        if (carDetailJson != null){
            val carDetail: CarDetail = Gson().fromJson(carDetailJson, CarDetail::class.java)
            title = carDetail.brandName

        }*/
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)






    }
}