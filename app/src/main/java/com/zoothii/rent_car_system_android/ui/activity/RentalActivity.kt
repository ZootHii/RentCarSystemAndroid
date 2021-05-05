package com.zoothii.rent_car_system_android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zoothii.rent_car_system_android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental)

/*        if (Helper.data is CarDetail) { // TODO no need any more for replace instead I used navigation
            val fragment = CarDetailFragment()
            if (savedInstanceState == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.car_detail_fragment_container, fragment)
                ft.commit()
            }
        }*/
    }
}
