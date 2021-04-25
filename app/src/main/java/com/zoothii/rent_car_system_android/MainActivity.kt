package com.zoothii.rent_car_system_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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


        // todo change of navController -> fragment to FragmentContainerView
        // todo do this to make it work
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController






        //val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )*/

        //setupActionBarWithNavController(navController, appBarConfiguration)

        /*val toolbar: Toolbar = findViewById(R.id.toolbar_cars_detail)

        setSupportActionBar(toolbar)*/
        navView.setupWithNavController(navController)






    }
}