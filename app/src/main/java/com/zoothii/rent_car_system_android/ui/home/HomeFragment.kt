package com.zoothii.rent_car_system_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarCardAdapter
import com.zoothii.rent_car_system_android.model.Car
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.model.CarImage
import com.zoothii.rent_car_system_android.view.CarImageViewModel
import com.zoothii.rent_car_system_android.view.CarViewModel

class HomeFragment : Fragment() {

    //private lateinit var homeViewModel: HomeViewModel
    private lateinit var carViewModel: CarViewModel
    private lateinit var carImageViewModel: CarImageViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var cars: List<Car>
    private lateinit var carDetailList: List<CarDetail>
    private var carImageList: ArrayList<CarImage> = ArrayList()
    private var carImageList2: ArrayList<Int> = ArrayList()
    private lateinit var carImage: CarImage
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        carImageViewModel = ViewModelProvider(this).get(CarImageViewModel::class.java)

        fun setCarDetailsRecyclerView(carDetailList: List<CarDetail>) {
            recyclerView = view.findViewById(R.id.recycler_view)
            recyclerView.adapter = CarCardAdapter(carDetailList)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        carViewModel.getAllCarsDetailsWithPreviewFirstImage()

        carViewModel.carDetailDataResponse.observe(viewLifecycleOwner, { responseCarDetailData ->
            if (responseCarDetailData.success) {
                carDetailList = responseCarDetailData.data.toCollection(ArrayList())
                setCarDetailsRecyclerView(carDetailList)


            } else {
                Log.d("Message", responseCarDetailData.message.toString())
                Log.d("Success", responseCarDetailData.success.toString())
            }
        })

        return view
    }

}


// TODO unnecessary codes maybe I can use later


/*carImageViewModel.getAllCarImages()
                    carImageViewModel.carImageDataResponse.observe(
                        viewLifecycleOwner,
                        Observer { responseCarImageData ->

                            if (responseCarImageData.success) {

                                carImageList = responseCarImageData.data.toCollection(ArrayList())

                                for (car in responseCarImageData.data) {
                                    Log.d("Response CAR", car.toString())
                                }
                                // carimage in carid si car listteki car id ile eşleşen ilk resmi getir


                                for (i in carImageList.indices) {
                                    for (j in carDetailList.indices) {
                                        if (carDetailList[j].id == carImageList[i].carId && carDetailList[j].previewCarImage.isNullOrEmpty()) {
                                            carDetailList[j].previewCarImage =
                                                carImageList[i].imagePath
                                        }
                                        if (carDetailList[j].previewCarImage.isNullOrEmpty()) {
                                            carDetailList[j].previewCarImage = ""
                                        }
                                    }

                                }




                                setCarDetailsRecyclerView(carDetailList)
                                CarCardAdapter(carDetailList).notifyDataSetChanged()


                            }

                        })*/


/*        carViewModel.getAllCars()
        carViewModel.carDataResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.success){
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
                for (car in response.data){
                    Log.d("Response CAR", car.toString())
                }
                cars = response.data.toCollection(ArrayList())
                setRecyclerView(cars)
                CarCardAdapter(cars).notifyDataSetChanged()
            }
            else
            {
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
            }
        })*/


/*fun setPrev(card: List<CarDetail>) {
            card.forEach { carDetail ->
                carImageViewModel.getCarPreviewFirstImageByCarId(carDetail.id)
                carImageViewModel.carImageSingleDataResponse.observe(viewLifecycleOwner, Observer { responseCarImageSingleData ->
                    if (responseCarImageSingleData.success) {

                        Log.d("Success", responseCarImageSingleData.success.toString())

                        carImage = responseCarImageSingleData.data

                        carDetail.previewCarImage = carImage.imagePath


                    } else {
                        Log.d("Message", responseCarImageSingleData.message.toString())
                        Log.d("Success", responseCarImageSingleData.success.toString())
                    }
                })
            }
            setCarDetailsRecyclerView(carDetailList)

        }*/


//Log.d("SİZE CAR", carDetailList.size.toString())


/*for (i in carDetailList.indices){ // single for loop in kotlin ÇOK SAÇMA


    count++
    Log.d("EZİYET", count.toString())
    //carImageViewModel.getCarImagesByCarId(carDetailList[i].id) // test
    //Log.d("INDEX", carDetailList[i].id.toString())
    carDetailList.forEach { carDetail ->
        if (carDetail.previewCarImage.isNullOrEmpty()){
            carDetail.previewCarImage = ""
        }
    }

    *//*carImageViewModel.getCarPreviewFirstImageByCarId(carDetailList[i].id)
                    carImageViewModel.carImageSingleDataResponse.observe(viewLifecycleOwner, Observer { responseCarImageSingleData ->
                        if (responseCarImageSingleData.success){
                            *//**//*Log.d("Message", responseCarImageSingleData.message.toString())
                            Log.d("Success", responseCarImageSingleData.success.toString())*//**//*

                            carImage = responseCarImageSingleData.data

                            //carImageList2.add(i)
                            //carImageList.add(carImage)

                            //Log.d("IMAGE", carImage.carId.toString() +" "+ carImage.id.toString())
                            *//**//*count++
                            Log.d("EZİYET", count.toString())*//**//*
                            carDetailList[i].previewCarImage = carImage.imagePath

                            setCarDetailsRecyclerView(carDetailList)
                            CarCardAdapter(carDetailList).notifyDataSetChanged()
                        }
                        else{
                            Log.d("Message", responseCarImageSingleData.message.toString())
                            Log.d("Success", responseCarImageSingleData.success.toString())
                        }




                        *//**//*setCarDetailsRecyclerView(carDetailList)
                        CarCardAdapter(carDetailList).notifyDataSetChanged()
                        Log.d("SİZE CAR", carDetailList.size.toString())
                        Log.d("SİZE image", carImageList2.size.toString())*//**//*



                    })*//*

                    // TEST
                    *//*carImageViewModel.carImageDataResponse.observe(viewLifecycleOwner, Observer { responseCarImageData ->
                        if (responseCarImageData.success){
                            Log.d("Message", responseCarImageData.message.toString())
                            Log.d("Success", responseCarImageData.success.toString())

                            for (car in responseCarImageData.data){
                                Log.d("Response CAR IMAGE", car.toString())
                            }

                            carImageList = responseCarImageData.data.toCollection(ArrayList())
                        }
                        else{
                            Log.d("Message", responseCarImageData.message.toString())
                            Log.d("Success", responseCarImageData.success.toString())
                        }
                    })*//*

                }*/


//carViewModel = ViewModelProvider(this).get(CarViewModel(CarRepository.create())::class.java)


/*        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

/*setRecylerView(cars)*/


/*        val nameObserver = Observer<DataResponseModel<Car>> { response ->
            if (response.success){
                Log.d("Message", response.success.toString())
                Log.d("Success", response.success.toString())

                cars = response.data.toCollection(ArrayList())

                setRecylerView(cars)
            }
        }

        carViewModel.currentName.observe(viewLifecycleOwner, nameObserver)*/

/*carViewModel.currentName.observe(viewLifecycleOwner, Observer { response ->
    if (response.success){
        Log.d("Message", response.message.toString())
        Log.d("Success", response.success.toString())
        for (car in response.data){
            Log.d("Response CAR", car.toString())
        }
        cars = response.data.toCollection(ArrayList())
        setRecylerView(cars)
    }
    else
    {
        Log.d("Message", response.message.toString())
        Log.d("Success", response.success.toString())
    }

})*/

/*carViewModel.getAllCars().observe(viewLifecycleOwner, Observer { response ->
            if (response.success){
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
                for (car in response.data){
                    Log.d("Response CAR", car.toString())
                }
                cars = response.data.toCollection(ArrayList())
                //setRecylerView(cars)
                CarCardAdapter(cars).notifyDataSetChanged()
            }
            else
            {
                Log.d("Message", response.message.toString())
                Log.d("Success", response.success.toString())
            }
        })*/


/*        CarRepository.create().getAllCars().observe(viewLifecycleOwner, Observer { response ->
            if (response.success){
                cars = response.data.toCollection(ArrayList())
                setRecylerView(cars)
                Log.d("AHMET", "aaaa")
            }
        })*/
