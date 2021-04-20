package com.zoothii.rent_car_system_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarCardAdapter
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.view.CarViewModel

class HomeFragment : Fragment() {

    //private lateinit var homeViewModel: HomeViewModel
    private lateinit var carViewModel: CarViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var cars: List<Car>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
/*        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        carViewModel.getAllCars().observe(viewLifecycleOwner, Observer { response ->
            if (response.success){
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
                for (car in response.data){
                    Log.d("Response CAR", car.toString())
                }
                cars = response.data.toCollection(ArrayList())
                recyclerView = root.findViewById(R.id.recycler_view)
                recyclerView.adapter = CarCardAdapter(cars)
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
            else
            {
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
            }
        })


        return root
    }

}