package com.zoothii.rent_car_system_android.ui.home

import android.R.attr.category
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.ui.dashboard.DashboardFragment
import com.zoothii.rent_car_system_android.util.Helper


class CarDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)






        val intent = intent


        if (Helper.data is CarDetail){
            val carDetail = Helper.data as CarDetail
            Log.d("DATA CAR DETAIL", carDetail.toString())
            val fragment = DashboardFragment()
            val args = Bundle()
            /*args.putInt("CategoryId", category.getId())
            args.putString("CallerActivity", callerActivity)*/

            if (savedInstanceState == null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragment.arguments = args
                ft.replace(R.id.blogs_fragment_container, fragment)
                ft.commit()
            }
        }


    }
}