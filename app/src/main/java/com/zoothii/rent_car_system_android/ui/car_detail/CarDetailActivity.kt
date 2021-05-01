package com.zoothii.rent_car_system_android.ui.car_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.ui.cars_detail.CarsDetailFragment
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        if (Helper.data is CarDetail) {
            val fragment = CarDetailFragment()
            if (savedInstanceState == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.car_detail_fragment_container, fragment)
                ft.commit()
            }
        }
/*        if (Helper.data == 1){
            val fragment = CarsDetailFragment()
            if (savedInstanceState == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.car_detail_fragment_container, fragment)
                ft.commit()
            }
        }*/
    }
}
