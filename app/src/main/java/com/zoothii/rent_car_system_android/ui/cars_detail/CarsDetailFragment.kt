package com.zoothii.rent_car_system_android.ui.cars_detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoothii.rent_car_system_android.R
import com.zoothii.rent_car_system_android.adapter.CarsDetailAdapter
import com.zoothii.rent_car_system_android.databinding.FragmentCarsDetailBinding
import com.zoothii.rent_car_system_android.model.CarDetail
import com.zoothii.rent_car_system_android.ui.car_detail.CarDetailActivity
import com.zoothii.rent_car_system_android.util.Helper
import com.zoothii.rent_car_system_android.view_and_factory.car.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsDetailFragment : Fragment() {

    private val carViewModel: CarViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var carCardAdapter: CarsDetailAdapter
    private lateinit var carDetailList: ArrayList<CarDetail>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fragmentCarsDetailBinding = FragmentCarsDetailBinding.inflate(
            inflater,
            container,
            false
        )
        val fragmentRoot = fragmentCarsDetailBinding.root
        val toolbar = fragmentCarsDetailBinding.toolbarCarsDetail
        toolbar.title = "Cars"
        toolbar.inflateMenu(R.menu.search_bar_menu)
        val searchItem = toolbar.menu.findItem(R.id.navigation_search)
        val searchView = searchItem.actionView as SearchView
        val progressBar = fragmentCarsDetailBinding.progressBar
        recyclerView = fragmentCarsDetailBinding.recyclerView

        carCardAdapter = CarsDetailAdapter(fragmentRoot.context) { carDetail, carsDetailList ->
            val intent = Intent(activity, CarDetailActivity::class.java)

            Helper.data = carDetail

            //intent.putExtra("carDetail", "Gson().toJson(carDetail)111") // TODO does not work with huge data so I used helper static object
            //carCardAdapter.setCarDetails(carDetailList)
            startActivity(intent)
        }

        recyclerView.adapter = carCardAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentRoot.context)

        Helper.progressBarShow(progressBar, true)
        carViewModel.getAllCarsDetailsWithPreviewFirstImage().observe(
            viewLifecycleOwner,
            { responseCarDetailData ->
                if (responseCarDetailData.success) {

                    carDetailList = responseCarDetailData.data.toCollection(ArrayList())
                    carCardAdapter.setCarDetails(carDetailList)
                    Helper.progressBarShow(progressBar, false)

                } else {
                    Log.d("Message", responseCarDetailData.message.toString())
                    Log.d("Success", responseCarDetailData.success.toString())
                }
            })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                carCardAdapter.filter.filter(newText)
                return true
            }

        })

        return fragmentRoot
    }
}


// TODO unnecessary codes maybe I can use later

//(requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

/*    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_bar_menu, menu)

*//*        val search = menu.findItem(R.id.navigation_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "aaaa"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                carCardAdapter.filter(newText)
                return true
            }

        })*//*
    }*/

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
