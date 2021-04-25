package com.zoothii.rent_car_system_android.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.util.Helper

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val toolbar = root.findViewById<Toolbar>(R.id.toolbar2_cars_detail)
        toolbar?.hideOverflowMenu()
        toolbar?.title = "2"

/*      val imageView1: ImageView = root.findViewById(R.id.imageView1)
        val imageView2: ImageView = root.findViewById(R.id.imageView2)*/

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

            if (Helper.data is CarDetail) {
                val carDetail = Helper.data as CarDetail
                //.text = carDetail.brandName.toString()
            }
        })


        return root
    }
}