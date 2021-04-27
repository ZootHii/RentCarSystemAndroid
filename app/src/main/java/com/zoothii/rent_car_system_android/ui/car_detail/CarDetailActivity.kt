package com.zoothii.rent_car_system_android.ui.car_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        if (Helper.data is CarDetail) {
            val fragment = CarDetailFragment()
            val args = Bundle()
            /*args.putInt("CategoryId", category.getId()) // TODO does not work with huge data so I used helper static object
            args.putString("CallerActivity", callerActivity)*/

            if (savedInstanceState == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragment.arguments = args
                ft.replace(R.id.car_detail_fragment_container, fragment)
                ft.commit()
            }
        }
    }
}